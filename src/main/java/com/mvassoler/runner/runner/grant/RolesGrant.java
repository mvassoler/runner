package com.mvassoler.runner.runner.grant;

public final class RolesGrant {

    public static final String CREATE_CORRIDA = "hasAuthority('inserir_corrida')";
    public static final String DELETE_CORRIDA = "hasAuthority('excluir_corrida')";
    public static final String CREATE_PROVA = "hasAuthority('inserir_prova')";
    public static final String UPDATE_PROVA = "hasAuthority('atualizar_prova')";
    public static final String DELETE_PROVA = "hasAuthority('excluir_prova')";
    public static final String SUPER_USER = "hasAuthority('super_user')";

    private RolesGrant() {

    }

}
