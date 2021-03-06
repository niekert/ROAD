package road.policesystem.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Niek on 13/05/14.
 *  Aidas 2014
 */
public class Utilities
{
    /**
     * Get a context root of the deployed web application.
     * @return The contextRoot is the last part after / of the URL (without parameters).
     */
    public static String getContextRoot()
    {
        return  FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    /**
     * Get the full web address of the deployed web application.
     * @return The full web address (without parameters).
     */
    public static String getHostnameAndContext()
    {
        HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();

        return "http://" + origRequest.getServerName() + ":"+ origRequest.getServerPort() + getContextRoot();

    }
}
