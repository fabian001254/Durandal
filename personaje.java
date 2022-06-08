import java.util.Scanner;

class personaje{
    public static void main(String[] args) {
        String[] personajes = new String[13];
        personajes[0] = "Radamel Falcao Garcia";
        personajes[1] = "Goku";
        personajes[2] = "Michael Jordan";
        personajes[3] = "Eminem";
        personajes[4] = "Darth Vader";
        personajes[5] = "Adam Sandler";
        personajes[6] = "Bruce Wayne";
        personajes[7] = "Tin Tin";
        personajes[8] = "Ayudante de santa";
        personajes[9] = "Joe Biden";
        personajes[10] = "Jose Saramago";
        personajes[11] = "Gunter Grass";
        personajes[12] = "Kim Jong Un";

        System.out.println("Lista de personajes: ");
        for(int i= 0; i<13; i++){
            System.out.println(personajes[i]+"\n");
        }

        System.out.println("Su personaje es humano?");
        Scanner leer = new Scanner(System.in);
        String op = leer.nextLine();
        if(op.equals("si")){
            op = "";
            System.out.println("Su personaje es un presidente?");
            op = leer.nextLine();
            if(op.equals("si")){
                System.out.println("Su personaje es de corea del norte");
                op = "";
                op = leer.nextLine();
                if(op.equals("si")){
                    System.out.println("Su personaje es: "+personajes[12]);
                }else{
                    System.out.println("Su personaje es: "+personajes[9]);
                }
            }else{
                System.out.println("Su personaje es una celebridad?");
                op = "";
                op = leer.nextLine();
                if(op.equals("si")){
                    System.out.println("Su personaje es un jugador profesional?");
                    op = "";
                    op = leer.nextLine();
                    if(op.equals("si")){
                        System.out.println("Su personaje es jugador de futbol?");
                        op = "";
                        op = leer.nextLine();
                        if(op.equals("si")){
                            System.out.println("Su personaje es: "+personajes[0]);
                        }else{
                            System.out.println("Su personaje es: "+personajes[2]);
                        }
                    }else{
                        System.out.println("Su personaje es un cantante?");
                        op = "";
                        op = leer.nextLine();
                        if(op.equals("si")){
                            System.out.println("Su personaje es: "+personajes[3]);
                        }else{
                            System.out.println("Su personaje es: "+personajes[5]);
                        }
                    }
                }else{
                    System.out.println("Su personaje es un escritor?");
                    op = "";
                    op = leer.nextLine();
                    if(op.equals("si")){
                        System.out.println("Su personaje es aleman?");
                        op = "";
                        op = leer.nextLine();
                        if(op.equals("si")){
                            System.out.println("Su personaje es: "+personajes[11]);
                        }else{
                            System.out.println("Su personaje es: "+personajes[10]);
                        }
                    }else{
                        System.out.println("Su personaje es un personaje de pelicula?");
                        op = "";
                        op = leer.nextLine();
                        if(op.equals("si")){
                            System.out.println("El logo de su personaje se familiariza con un animal?");
                            op = "";
                            op = leer.nextLine();
                            if(op.equals("si")){
                                System.out.println("Su personaje es: "+personajes[6]);
                            }else{
                                System.out.println("Su personaje es: "+personajes[4]);
                            }
                        }else{
                            System.out.println("No existe su personaje");
                        }
                    }
                }
            }
        }else{
            System.out.println("Su personaje es un detective?");
            op = "";
            op = leer.nextLine();
            if(op.equals("si")){
                System.out.println("Su personaje es: "+personajes[7]);
            }else{
                System.out.println("Su personaje es: "+personajes[1]);
            }
        }
    }
}