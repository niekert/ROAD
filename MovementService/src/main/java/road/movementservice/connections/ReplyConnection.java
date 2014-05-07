package road.movementservice.connections;

import road.movementdts.connections.MovementConnection;
import road.movementdts.helpers.Pair;
import road.movementdts.serializers.Serializer;
import javax.jms.*;
import javax.naming.InitialContext;
import java.util.ArrayList;

/**
 * Created by geh on 10-4-14.
 */
public class ReplyConnection extends MovementConnection implements MessageListener
{
    private ConnectionListener listener;

    public ReplyConnection(String factoryName, String listenTo, ConnectionListener listener)
    {
        try
        {
            this.serializer = new Serializer();
            this.context = new InitialContext();

            this.factory = (ConnectionFactory)this.context.lookup(factoryName);
            this.connection = this.factory.createConnection();

            this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            this.producer = this.session.createProducer(null);

            this.listenTo = (Destination)this.context.lookup(listenTo);
            this.consumer = this.session.createConsumer(this.listenTo);
            this.consumer.setMessageListener(this);
            this.listener = listener;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message)
    {
        try
        {
            TextMessage textMessage = (TextMessage)message;
            Pair<String, ArrayList<Object>> pair = this.serializer.deSerialize(textMessage.getText(), Pair.class);
            String rawReply = this.listener.receive(pair);

            TextMessage reply = this.session.createTextMessage();
            reply.setText(rawReply);
            this.producer.send(message.getJMSReplyTo(), reply);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}