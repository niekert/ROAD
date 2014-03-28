//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.03.28 at 01:05:22 PM CET 
//

package osm.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for osmType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="osmType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="node" type="{}nodeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="way" type="{}wayType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="generator" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "osmType", propOrder = { "node", "way" })
public class OsmType
{

	protected List<NodeType> node;
	protected List<WayType> way;
	@XmlAttribute(name = "version")
	protected Float version;
	@XmlAttribute(name = "generator")
	protected String generator;

	/**
	 * Gets the value of the node property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the node property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getNode().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link NodeType }
	 * 
	 * 
	 */
	public List<NodeType> getNode()
	{
		if (node == null)
		{
			node = new ArrayList<NodeType>();
		}
		return this.node;
	}

	/**
	 * Gets the value of the way property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the way property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getWay().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link WayType }
	 * 
	 * 
	 */
	public List<WayType> getWay()
	{
		if (way == null)
		{
			way = new ArrayList<WayType>();
		}
		return this.way;
	}

	/**
	 * Gets the value of the version property.
	 * 
	 * @return possible object is {@link Float }
	 * 
	 */
	public Float getVersion()
	{
		return version;
	}

	/**
	 * Sets the value of the version property.
	 * 
	 * @param value
	 *            allowed object is {@link Float }
	 * 
	 */
	public void setVersion(Float value)
	{
		this.version = value;
	}

	/**
	 * Gets the value of the generator property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getGenerator()
	{
		return generator;
	}

	/**
	 * Sets the value of the generator property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setGenerator(String value)
	{
		this.generator = value;
	}

}