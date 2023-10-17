package br.upf.eventos.service

import br.upf.eventos.converters.EventoConverter
import br.upf.eventos.dtos.EventoDTO
import br.upf.eventos.dtos.EventoResponseDTO
import br.upf.eventos.model.Evento
import br.upf.eventos.repository.EventoRepository
import org.springframework.stereotype.Service

@Service
class EventoService(private val repository: EventoRepository,
    val converter: EventoConverter) {

    fun listar(): List<EventoResponseDTO> {
        return repository.findAll()
            .map(converter::toEventoResponseDTO)
    }

    fun buscarPorId(id: Long): EventoResponseDTO {
        val evento = repository.findAll().first { it.id == id }
        return converter.toEventoResponseDTO(evento)
    }

    fun cadastrar(dto: EventoDTO) {
        repository.cadastrar(converter.toEvento(dto))
    }

    fun atualizar(id: Long, dto: EventoDTO) {
        val evento = repository.findAll().first { it.id == id }
        repository.update(evento, converter.toEvento(dto))
    }

}