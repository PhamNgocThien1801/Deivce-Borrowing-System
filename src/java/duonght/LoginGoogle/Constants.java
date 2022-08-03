/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duonght.LoginGoogle;

/**
 *
 * @author Trung Duong
 */
public class Constants {

    public static String GOOGLE_CLIENT_ID = "33568893407-i7p94f2ca7var420dpis79903h4o46ut.apps.googleusercontent.com";

    public static String GOOGLE_CLIENT_SECRET = "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";

    public static String GOOGLE_REDIRECT_URI = "http://localhost:8084/DeviceManagement/LoginHandler";

    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public static String GOOGLE_GRANT_TYPE = "authorization_code";
}
