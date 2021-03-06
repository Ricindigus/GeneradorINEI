package pe.com.ricindigus.generadorinei.parser;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import pe.com.ricindigus.generadorinei.pojos.OpcionSpinner;
import pe.com.ricindigus.generadorinei.pojos.Pagina;
import pe.com.ricindigus.generadorinei.pojos.Usuario;

import static android.os.Environment.getExternalStorageDirectory;

/**
 * Created by dmorales on 28/12/2017.
 */

public class PaginaPullParser {
    public static final String PAGINA_ID = "ID";
    public static final String PAGINA_MODULO = "MODULO";
    public static final String PAGINA_IDP1 = "IDP1";
    public static final String PAGINA_TP1 = "TIPO1";
    public static final String PAGINA_IDP2 = "IDP2";
    public static final String PAGINA_TP2 = "TIPO2";
    public static final String PAGINA_IDP3 = "IDP3";
    public static final String PAGINA_TP3 = "TIPO3";
    public static final String PAGINA_IDP4 = "IDP4";
    public static final String PAGINA_TP4 = "TIPO4";
    public static final String PAGINA_IDP5 = "IDP5";
    public static final String PAGINA_TP5 = "TIPO5";
    public static final String PAGINA_IDP6 = "IDP6";
    public static final String PAGINA_TP6 = "TIPO6";
    public static final String PAGINA_IDP7 = "IDP7";
    public static final String PAGINA_TP7 = "TIPO7";
    public static final String PAGINA_IDP8 = "IDP8";
    public static final String PAGINA_TP8 = "TIPO8";
    public static final String PAGINA_IDP9 = "IDP9";
    public static final String PAGINA_TP9 = "TIPO9";
    public static final String PAGINA_IDP10 = "IDP10";
    public static final String PAGINA_TP10 = "TIPO10";

    private Pagina currentPagina = null;
    private String currentTag = null;
    ArrayList<Pagina> paginas = new ArrayList<Pagina>();

    public ArrayList<Pagina> parseXML(Context context){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            try {
                InputStream stream = context.getAssets().open("paginas.xml");
                xpp.setInput(stream,null);

                int eventType = xpp.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        handleStarTag(xpp.getName());
                    }else if(eventType == XmlPullParser.END_TAG){
                        currentTag = null;
                    }else if(eventType == XmlPullParser.TEXT){
                        handleText(xpp.getText());
                    }
                    eventType = xpp.next();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return paginas;
    }

    public ArrayList<Pagina> parseXML(Context context, String archivo){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            FileInputStream fis = null;
            try {
                File nuevaCarpeta = new File(getExternalStorageDirectory(), "GENERADOR");
                File file = new File(nuevaCarpeta, archivo);
                FileInputStream fileInputStream = new FileInputStream(file);
                fis = new FileInputStream(file);
                xpp.setInput(fis, null);
                int eventType = xpp.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    if(eventType == XmlPullParser.START_TAG){
                        handleStarTag(xpp.getName());
                    }else if(eventType == XmlPullParser.END_TAG){
                        currentTag = null;
                    }else if(eventType == XmlPullParser.TEXT){
                        handleText(xpp.getText());
                    }
                    eventType = xpp.next();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return paginas;
    }

    private void handleText(String text) {
        String xmlText = text;
        if(currentPagina!= null && currentTag != null){
            switch (currentTag){
                case PAGINA_ID:currentPagina.setID(xmlText);break;
                case PAGINA_MODULO:currentPagina.setMODULO(xmlText);break;
                case PAGINA_IDP1:currentPagina.setIDP1(xmlText);break;
                case PAGINA_TP1:currentPagina.setTIPO1(xmlText);break;
                case PAGINA_IDP2:currentPagina.setIDP2(xmlText);break;
                case PAGINA_TP2:currentPagina.setTIPO2(xmlText);break;
                case PAGINA_IDP3:currentPagina.setIDP3(xmlText);break;
                case PAGINA_TP3:currentPagina.setTIPO3(xmlText);break;
                case PAGINA_IDP4:currentPagina.setIDP4(xmlText);break;
                case PAGINA_TP4:currentPagina.setTIPO4(xmlText);break;
                case PAGINA_IDP5:currentPagina.setIDP5(xmlText);break;
                case PAGINA_TP5:currentPagina.setTIPO5(xmlText);break;
                case PAGINA_IDP6:currentPagina.setIDP6(xmlText);break;
                case PAGINA_TP6:currentPagina.setTIPO6(xmlText);break;
                case PAGINA_IDP7:currentPagina.setIDP7(xmlText);break;
                case PAGINA_TP7:currentPagina.setTIPO7(xmlText);break;
                case PAGINA_IDP8:currentPagina.setIDP8(xmlText);break;
                case PAGINA_TP8:currentPagina.setTIPO8(xmlText);break;
                case PAGINA_IDP9:currentPagina.setIDP9(xmlText);break;
                case PAGINA_TP9:currentPagina.setTIPO9(xmlText);break;
                case PAGINA_IDP10:currentPagina.setIDP10(xmlText);break;
                case PAGINA_TP10:currentPagina.setTIPO10(xmlText);break;
            }
        }
    }

    private void handleStarTag(String name) {
        if(name.equals("PAGINA")){
            currentPagina = new Pagina();
            paginas.add(currentPagina);
        }else{
            currentTag = name;
        }
    }
}
