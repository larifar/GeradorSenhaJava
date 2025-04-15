package challenge;

import challenge.service.GeradorSenha;
import challenge.service.TypeSenha;

public class Main {
    public static void main(String[] args) {
        GeradorSenha geradorSenha = new GeradorSenha();
        System.out.println(geradorSenha.gerarSenha(10, TypeSenha.BOTH, true));
        System.out.println(geradorSenha.gerarSenha(14, TypeSenha.BOTH, true));
    }
}