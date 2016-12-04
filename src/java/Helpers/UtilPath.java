/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helpers;

import java.io.File;

/**
 *
 * @author APRENDIZ
 */
public class UtilPath {
    
    public static String getPathDefinida(String path){
        if(path != null){
            StringBuilder rutaDefinida = new StringBuilder();
            for(int i = 0; i < path.length(); i++){
                String nuevaRuta = "";
                char characterValue = path.charAt(i);
                int asciiValue = (int) characterValue;
                int newAsciiValue = 0;
                StringBuilder newCharacterValue = new StringBuilder();
                if(asciiValue == 47 || asciiValue == 92){
                    for(int n = i + 1; asciiValue == 47 || asciiValue == 92; n++){
                        newAsciiValue = (int) path.charAt(n);
                        if(newAsciiValue == 47 || newAsciiValue == 92){
                            i = n-1;
                            asciiValue = -1;
                        } else{
                            newCharacterValue.append(path.charAt(n));
                        }
                    }
                    
                    String value = newCharacterValue.toString();
                    
                    if(value.equals("build")){
                        i = path.length();
                    } else{
                        rutaDefinida.append(File.separator).append(value);
                    }
                }
            }
            return rutaDefinida.toString();
        } else{
            return null;
        }
    }
    
    
    public static String getUrlDefinida(String ruta) {
        if (ruta != null) {
            StringBuilder rutaDefinida = new StringBuilder();
            for (int i = 0; i < ruta.length(); i++) {
                char characterValue = ruta.charAt(i);
                int asciiValue = (int) characterValue;
                int newAsciiValue = 0;
                StringBuilder newCharacterValue = new StringBuilder();
                if (asciiValue == 47 || asciiValue == 92) {
                    for (int n = i + 1; asciiValue == 47 || asciiValue == 92; n++) {
                        newAsciiValue = (int) ruta.charAt(n);
                        if (newAsciiValue == 47 || newAsciiValue == 92) {
                            i = n - 1;
                            asciiValue = -1;
                        } else {
                            newCharacterValue.append(ruta.charAt(n));
                        }

                    }
                    String value = newCharacterValue.toString();

                    if (value.equals("build")) {
                        i = ruta.length();
                    } else {
                        rutaDefinida.append(File.separator).append(value);
                    }
                } else {
                    rutaDefinida.append(characterValue);
                }

            }
            return rutaDefinida.toString();

        } else {
            return null;
        }
    }    
}