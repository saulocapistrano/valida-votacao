    package com.cooperevoto.application.usecase.votacao;

    import com.cooperevoto.api.v1.dto.request.VotoRequest;
    import com.cooperevoto.api.v1.dto.response.VotoResponse;
    import com.cooperevoto.domain.model.Voto;
    import com.cooperevoto.domain.repository.PautaRepository;
    import com.cooperevoto.domain.repository.SessaoVotacaoRepository;
    import com.cooperevoto.domain.repository.VotoRepository;
    import com.cooperevoto.exception.BusinessException;
    import com.cooperevoto.infrastructure.client.cpf.service.CpfValidationService;
    import jakarta.persistence.EntityNotFoundException;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.stereotype.Service;

    import java.time.LocalDateTime;

    @Service
    @RequiredArgsConstructor
    public class CadastrarVotoUseCaseImpl implements CadastrarVotoUseCase {

        private final VotoRepository votoRepository;
        private final PautaRepository pautaRepository;
        private final SessaoVotacaoRepository sessaoRepository;
        private final CpfValidationService cpfValidationService;

        @Override
        public VotoResponse executar(VotoRequest request) {
            if (votoRepository.existsByCpfAndPautaId(request.getCpf(), request.getPautaId())) {
                throw new BusinessException("Associado já votou nesta pauta", HttpStatus.UNPROCESSABLE_ENTITY);
            }

            var result = cpfValidationService.validar(request.getCpf());

            if ("UNABLE_TO_VOTE".equalsIgnoreCase(result.getStatus())) {
                throw new BusinessException("Associado não está apto a votar", HttpStatus.UNPROCESSABLE_ENTITY);
            }

            var pauta = pautaRepository.findById(request.getPautaId())
                    .orElseThrow(() -> new EntityNotFoundException("Pauta não encontrada"));

            var sessao = sessaoRepository.findByPautaId(request.getPautaId())
                    .orElseThrow(() -> new IllegalStateException("Sessão não encontrada para a pauta"));

            var agora = LocalDateTime.now();

            if (agora.isAfter(sessao.getDataFechamento())) {
                throw new BusinessException("Sessão de votação já encerrada", HttpStatus.UNPROCESSABLE_ENTITY);
            }

            var voto = Voto.builder()
                    .cpf(request.getCpf())
                    .voto(request.getVoto())
                    .pauta(pauta)
                    .sessao(sessao)
                    .dataVoto(agora)
                    .build();

            var salvo = votoRepository.save(voto);

            return new VotoResponse(
                    salvo.getId(),
                    salvo.getCpf(),
                    salvo.getVoto(),
                    salvo.getPauta().getId(),
                    salvo.getDataVoto()
            );
        }
    }
