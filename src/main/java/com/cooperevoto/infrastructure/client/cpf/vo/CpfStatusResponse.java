package com.cooperevoto.infrastructure.client.cpf.vo;

public class CpfStatusResponse {
    private String status; // ABLE_TO_VOTE, UNABLE_TO_VOTE

    public CpfStatusResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
