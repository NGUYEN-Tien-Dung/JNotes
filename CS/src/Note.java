/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.util.Vector;

public class JavaApplication3 
{
    private static Vector<String> Redondense_HASHTAG(Vector<String> VEC_A_TRIER)
    { 
        // Variables de parcours du vecteur :
        int i = 1;
        int j;
        
        // Variables de comptage : 
        int cpt = 0;                // Nombre d'occurences d'un HASHTAG donné
        Vector index;             // Indexs des occurences supérieures à 1 des HESHTAG présent plusieurs fois 
        index = new Vector(1,1);
        String tmp = VEC_A_TRIER.elementAt(0);
        
        // Recherche des coordonées des élément présents deux fois ou plus : 
        for(i=1;i<VEC_A_TRIER.size()-1;i++)
        {
            for(j=i;i<VEC_A_TRIER.size()-1;j++)
            {
                //if(VEC_A_TRIER.elementAt(j).equals(tmp)==true)
                //{
                    //index.add(j);
                //}
            }
            tmp = VEC_A_TRIER.elementAt(i);
        }
        // Suppression des éléments présents deux fois ou plus : 
        //for(i=0;i<index.size()-1;i++)
        //{
                //VEC_A_TRIER.removeElementAt(((int)index.elementAt(i)));
        //}
        
        return VEC_A_TRIER;
    }
    
    public static void main(String[] args)
    {
        int i;
        String a = "Rafale";
        String b = "F-22";
        String c = "F-22";
        String d = "Rafale";
        String e = "Su-35";
        Vector<String> v;
        Vector<String> u;
        v = new Vector(1,1);
        v.add(a);
        v.add(b);
        v.add(c);
        v.add(d);
        v.add(e);
        //System.out.println("Vecteur AVANT l'opération de tri :\n");
        //for(i=0;i<v.size();i++)
        //{
            //System.out.println(v.elementAt(i));
        //}
        System.out.println("Vecteur APRES l'opération de tri :\n");
      
        for(i=0;i<Redondense_HASHTAG(v).size()-1;i++)
        {
            System.out.println(Redondense_HASHTAG(v).elementAt(i));
        }        
    }   
}
