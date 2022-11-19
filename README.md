# Curso4semana4

<p>Se incluye sqlite para la tabla de likes. Creo una clase aislada Tabla_Mascotas que gestiona todo lo relacionado con su entidad.</p>


public class TablaMascotas {

    private static final String TABLA_MASCOTAS="TMASCOTAS";
    private static final String TABLA_MASCOTAS_COLUMN_ID="Id";
    private static final String TABLA_MASCOTAS_COLUMN_NOMBRE="Nombre";
    private static final String TABLA_MASCOTAS_COLUMN_LIKES="Likes";
    private static final String TABLA_MASCOTAS_COLUMN_IDFOTO="IdFoto";

    public static final String SQL_CREATE_TABLA_MASCOTAS = String.format(
            "CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY, %s TEXT, %s INTEGER, %s TEXT)",
            TABLA_MASCOTAS,
            TABLA_MASCOTAS_COLUMN_ID,
            TABLA_MASCOTAS_COLUMN_NOMBRE,
            TABLA_MASCOTAS_COLUMN_LIKES,
            TABLA_MASCOTAS_COLUMN_IDFOTO);

    public static final String UPDATE_LIKES_BY_ID="UPDATE "+ TABLA_MASCOTAS +
            " SET "+ TABLA_MASCOTAS_COLUMN_LIKES + " = "+ TABLA_MASCOTAS_COLUMN_LIKES + " + 1 " +
            " WHERE "+TABLA_MASCOTAS_COLUMN_ID+" = ";

    public static final String SELECT_ORDER_ID ="SELECT * FROM " + TABLA_MASCOTAS +
                                                " ORDER BY " + TABLA_MASCOTAS_COLUMN_ID;
    public static final String SELECT_5_FAVORITOS ="SELECT * FROM "+TABLA_MASCOTAS+
                                                " ORDER BY " +TABLA_MASCOTAS_COLUMN_LIKES+" DESC LIMIT 5";


//...

    public void addLike(Context ctx, int id) 
    public ArrayList<Mascota> getMascotasOrderedId(Context context)
    public ArrayList<Mascota> getMascotasOrderedLikes(Context context)
    public void insertMascota(Context ctx,int id, String nombre, int idFoto)


<p>Tab de votaciones. Datos persistentes en variable estática.</p>
<img src="https://github.com/Dalinkovic/Curso4semana4/blob/master/tab_votacion.jpg" width="480">
<hr><br>

<p>La estrella del Toolbar te lleva a ver los 5 mejores. Función de ordenación en el POJO con compareTo.</p>
<img src="https://github.com/Dalinkovic/Curso4semana4/blob/master/top_5_favoritos.jpg" width="480">
<hr><br>
<p>Menú con las tres opciones de Contacto, Cuenta Instagram y Acerca De</p>
<img src="https://github.com/Dalinkovic/Curso4semana4/blob/master/menucuenta.png" width="480">
<hr><br>
<p>Enviando con JavaMailAPI. Muestra mensaje enviando en tarea asincrónica.</p>
<img src="https://github.com/Dalinkovic/Curso4semana4/blob/master/javamail_sending.jpg" width="480">
<hr><br>

<p>Enviando con JavaMailAPI. Detecto si no se pudo enviar el correo con un listener.</p>
<img src="https://github.com/Dalinkovic/Curso4semana4/blob/master/javamail_ok.jpg" width="480">
<hr><br>
<p>Acerca de</p>
<img src="https://github.com/Dalinkovic/Curso4semana4/blob/master/acerca_de.jpg" width="480">
<br>

<p>Conectando un user de instagram</p>
<img src="https://github.com/Dalinkovic/Curso4semana4/blob/master/menucuenta2.png" width="480">
<hr><br>
<p>Endpont: https://graph.facebook.com/v8.0/17841447374397234/media?fields=id,caption,media_type,media_url,owner,username,like_count</p>
<p> Pon tu accesstoken y tu id Instagram business</b>

<p>Cargando desde la API de Facebook/Instagram, imagenes y likes.</p>
<p>Si tarda en cargar muestra unas fotos de una huella de perro en los placeholder.</p>
<img src="https://github.com/Dalinkovic/Curso4semana4/blob/master/instagram.png" width="480">
<hr><br>




<p>Video demostrando el funcionamiento</p>
https://user-images.githubusercontent.com/44303464/120105791-c74f1c00-c15a-11eb-9c0e-8cfd47d59468.mp4
<hr><br>

<p>Al dar like, se envía la mascota, junto al idInstagram y el token y el numero de likes actualizado en bd. 
Como no hay mas usuario que el mío, me hago los likes a mi mismo. Todo el problema es porque el endpoint de Instagram que solicitan ya no está permitido.</p>
<img src="https://github.com/Dalinkovic/Curso4Semana3/blob/master/Screenshot_2021-07-03-09-45-16-065_com.dossis.curso4semana3.jpg" width="480">
<hr><br>

<p>Video demostrando que suma el like y envía la notificación</p>
https://github.com/Dalinkovic/Curso4semana4/blob/master/video%20notificacion%20likes.mp4
<hr><br>

<p>Logs de nodejs con la entrada al endpoint send-likes.</p>
<img src="https://github.com/Dalinkovic/Curso4semana4/blob/master/captura-logs.JPG" width="480">
<hr><br>

<p>Pantallazo del emulador recibiendo notificación cada vez que se hace like</p>
<img src="https://github.com/Dalinkovic/Curso4Semana4/blob/master/demowearable2.JPG" width="480">
<hr><br>


<p>Video demostrando como envía los likes al emulador wearable e interactua navegando y mostrando distintas pantallas al tocar el smartwach.</p>
https://github.com/Dalinkovic/Curso4semana4/blob/master/videoWearable.mp4
<hr><br>
