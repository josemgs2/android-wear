package com.dossis.curso4semana4.restApi;

public final class Keys {

    /*Generar el access_token con
    https://developers.facebook.com/tools/explorer?method=GET&path=&version=v10.0
    y permisos
    pages_show_list
    ads_management
    business_management
    instagram_basic
    pages_read_engagement
    public_profile
     */

    public static final String VERSION = "/v8.0/";
    public static final String ROOT_URL = "https://graph.facebook.com" + VERSION;
    public static final String ACCESS_TOKEN = "EAAEBAwZAgzzoBABQpER2CiPyOEJ4lpunm5u8NVo7VChYVMWVQkTHVZCDshMPcvTMpn0r7ZAbsWhKnHHCZB1uvMfcGy10F275TVoMjVX7vA0RhVF9FqcfgaDVXyPZACVgNc2lNpIlX4OmI9rZCDOiI9g1LdZCFVPygCsSIxIB4ga6ZB695nRLGKJxjvstRXOOtXViZAEZCj5Je6F042V09sjscxALlzcmgGJfgjSvE2DElyC16gTSrKZCHcQ";
    public static final String KEY_ACCESS_TOKEN = "&access_token=";
    public static final String USER_ID = "17841447374397234";
    public static final String KEY_USER_MEDIA = "/media?fields=id,caption,media_type,media_url,owner,username,like_count";
    public static final String URL_USER_MEDIA = USER_ID + KEY_USER_MEDIA + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static String ACTIVE_USER_NAME="courseraDani";

}
