package it.intesys.codylab.service;

import it.intesys.codylab.api.model.SlotsApiDTO;
import it.intesys.codylab.mapper.SlotMapper;
import it.intesys.codylab.model.Slot;
import it.intesys.codylab.repository.SlotRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SlotServiceTest {

    @Mock
    SlotRepository slotRepository;
    @Mock
    SlotMapper slotMapper;
    @InjectMocks
    SlotService slotService;

    @DisplayName("Verifico che quando chiamo uno slot esistente torna dei dati consistenti")
    @Test
    void getSlotById() {
        // Arrange
        Long slotId = 1L;
        Slot slot = new Slot();
        slot.setId(slotId);
        SlotsApiDTO slotsApiDTO = new SlotsApiDTO();
        slotsApiDTO.setId(slotId);

        when(slotRepository.findById(slotId)).thenReturn(java.util.Optional.of(slot));
        when(slotMapper.toApiDTO(slot)).thenReturn(slotsApiDTO);

        // Act
        SlotsApiDTO result = slotService.getSlotById(slotId);

        // Assert
        assertNotNull(result);
        assertEquals(slotId, result.getId());
        verify(slotRepository).findById(slotId);
        verify(slotMapper).toApiDTO(slot);
    }
    @DisplayName("Verifico che quando chiamo uno slot NON esistente ritorna null")
    @Test
    void getSlotByIdNotFound() {
        // Arrange
        Long slotId = 1L;
        when(slotRepository.findById(slotId)).thenReturn(java.util.Optional.empty());

        // Act
        SlotsApiDTO result = slotService.getSlotById(slotId);

        // Assert
        assertNull(result);
        verify(slotRepository).findById(slotId);
    }

    @DisplayName("Verifico che quando creo uno slot, viene salvato correttamente e restituito")
    @Test
    void createSlot() {
        // 1. Preparazione
        SlotsApiDTO inputDto = new SlotsApiDTO();
        SlotsApiDTO outputDto = new SlotsApiDTO();
        outputDto.setId(1L);

        Slot slotEntity = new Slot();
        slotEntity.setId(1L);

        // 2. Configurazione mock
        when(slotMapper.toEntity(inputDto)).thenReturn(slotEntity);
        when(slotRepository.save(slotEntity)).thenReturn(slotEntity);
        when(slotMapper.toApiDTO(slotEntity)).thenReturn(outputDto);

        // 3. Esecuzione
        SlotsApiDTO result = slotService.createSlot(inputDto);

        // 4. Verifiche
        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(slotMapper).toEntity(inputDto);
        verify(slotRepository).save(slotEntity);
        verify(slotMapper).toApiDTO(slotEntity);
    }
    @DisplayName("Verifico che quando creo uno slot con ID nullo solleva una eccezione")
    @Test
    void createSlotWithNullId() {
        // 1. Preparazione
        SlotsApiDTO inputDto = new SlotsApiDTO();
        inputDto.setId(null);

        // 2. Configurazione mock
        when(slotMapper.toEntity(inputDto)).thenThrow(new IllegalArgumentException("ID non può essere nullo"));

        // 3. Esecuzione e Verifica
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            slotService.createSlot(inputDto);
        });

        assertEquals("ID non può essere nullo", exception.getMessage());
        verify(slotMapper).toEntity(inputDto);
    }

    @DisplayName("Verifico che quando aggiorno uno slot, viene aggiornato correttamente e restituito")
    @Test
    void updateSlot() {
        // 1. Preparazione
        Long slotId = 1L;
        SlotsApiDTO inputDto = new SlotsApiDTO();
        inputDto.setId(slotId);
        SlotsApiDTO outputDto = new SlotsApiDTO();
        outputDto.setId(slotId);

        Slot slotEntity = new Slot();
        slotEntity.setId(slotId);

        // 2. Configurazione mock
        when(slotMapper.toEntity(inputDto)).thenReturn(slotEntity);
        when(slotRepository.save(slotEntity)).thenReturn(slotEntity);
        when(slotMapper.toApiDTO(slotEntity)).thenReturn(outputDto);

        // 3. Esecuzione
        SlotsApiDTO result = slotService.updateSlot(slotId, inputDto);

        // 4. Verifiche
        assertNotNull(result);
        assertEquals(slotId, result.getId());

        verify(slotMapper).toEntity(inputDto);
        verify(slotRepository).save(slotEntity);
        verify(slotMapper).toApiDTO(slotEntity);
    }
    @DisplayName("Verifico che quando aggiorno uno slot con ID nullo solleva una eccezione")
    @Test
    void updateSlotWithNullId() {
        // 1. Preparazione
        Long slotId = null;
        SlotsApiDTO inputDto = new SlotsApiDTO();
        inputDto.setId(slotId);

        // 2. Configurazione mock
        when(slotMapper.toEntity(inputDto)).thenThrow(new IllegalArgumentException("ID non può essere nullo"));

        // 3. Esecuzione e Verifica
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            slotService.updateSlot(slotId, inputDto);
        });

        assertEquals("ID non può essere nullo", exception.getMessage());
        verify(slotMapper).toEntity(inputDto);
    }

    @DisplayName("Verifico che quando elimino uno slot, viene eliminato correttamente")
    @Test
    void deleteSlot() {
        // Arrange
        Long slotId = 1L;

        // Act
        slotService.deleteSlot(slotId);

        // Assert
        verify(slotRepository).deleteById(slotId);
    }
}