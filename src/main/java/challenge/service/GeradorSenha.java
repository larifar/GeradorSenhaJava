package challenge.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeradorSenha {

    public final String gerarSenha(int number, TypeSenha tipo, boolean specialCharacter){
        String password="";
        switch (tipo){
            case BOTH -> password = forTypeBOTH(number);
            case NUMBER -> password = forTypeNUMBER(number);
            case CHARACTER -> password = forTypeCHARACTER(number);
        }
        if (specialCharacter){
            password= addSpecial(password);
        }
        return password;

    }

    private String gerar(List<Character> list, int length){
        StringBuilder sb = new StringBuilder();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            sb.append(list.get(random.nextInt(list.size())));
        }
        return sb.toString();
    }

    private String forTypeCHARACTER(int length) {
        List<Character> characters = new ArrayList<>(letras());
        Collections.shuffle(characters);

        return gerar(characters, length);
    }

    private String forTypeNUMBER(int length) {
        List<Character> numbers = new ArrayList<>(numbers());
        Collections.shuffle(numbers);

        return gerar(numbers, length);
    }

    private List<Character> letras(){
        List<Character> alfabeto = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            alfabeto.add(c);
        }
        for (char c = 'a'; c <= 'z'; c++){
            alfabeto.add(c);
        }
        return alfabeto;
    }

    private String addSpecial(String password){
        List<Character> specials = specials();
        int index = ThreadLocalRandom.current().nextInt(password.length());
        char special = specials.get(ThreadLocalRandom.current().nextInt(specials.size()));

        StringBuilder sb = new StringBuilder(password);
        sb.setCharAt(index, special);

        return sb.toString();
    }

    private String forTypeBOTH(int length){
        List<Character> letras = new ArrayList<>(letras());
        List<Character> numbers = new ArrayList<>(numbers());
        List<Character> combinados = new ArrayList<>();
        Collections.shuffle(letras);
        Collections.shuffle(numbers);

        ThreadLocalRandom random = ThreadLocalRandom.current();
        combinados.add(letras.get(random.nextInt(letras.size())));
        combinados.add(numbers.get(random.nextInt(numbers.size())));

        for (int i = 0; i<length-2 ; i++) {
            if (random.nextBoolean()){
                combinados.add(letras.get(random.nextInt(letras.size())));
            } else{
                combinados.add(numbers.get(random.nextInt(numbers.size())));
            }
        }

        StringBuilder str = new StringBuilder();
        Collections.shuffle(combinados);
        for (Character c : combinados){
            str.append(c);
        }

        return str.toString();
    }

    private List<Character> numbers(){
        return List.of('0','1','2','3','4','5','6','7','8','9');
    }

    private List<Character> specials(){
        return List.of('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '[', ']', '{', '}', ';', ':', ',', '.', '<', '>', '/', '?');
    }
}
