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

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class SlotServiceTest {
    @Mock
    SlotRepository slotRepository;
    @Mock
    SlotMapper slotMapper;

    @InjectMocks
    SlotService slotService;

    @DisplayName("Verifico che quando chiamo uno slot esistente torna dei dati consistenti")
    @Test
    void getSlotById() {
        //ARRANGE
        Slot slot = new Slot();
        slot.setId(1L);
        slot.setDurata(10);

        SlotsApiDTO slotsApiDTO = new SlotsApiDTO();
        slotsApiDTO.setId(1L);
        slotsApiDTO.setDurata(10);
        
        when(slotRepository.findById(1L)).thenReturn(Optional.of(slot));
        when(slotMapper.toApiDTO(slot)).thenReturn(slotsApiDTO);
        // ACT
        SlotsApiDTO slotResult = slotService.getSlotById(1L);
        // ASSERT
        assertNotNull(slotResult);
        assertEquals(1L, slotResult.getId());
        assertEquals(10, slotResult.getDurata());
        verify(slotRepository, times(1)).findById(1L);
        verify(slotMapper, times(1)).toApiDTO(slot);
    }

    @DisplayName("Verifico che quando chiamo uno slot NON esistente solleva una eccezione")
    @Test
    void getSlotByIdNotFound() {
        // ARRANGE
        when(slotRepository.findById(1L)).thenReturn(Optional.empty());

        // ACT & ASSERT
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            slotService.getSlotById(1L);
        });

        assertEquals("Slot not found with id: 1", exception.getMessage());
        verify(slotRepository, times(1)).findById(1L);
        verify(slotMapper, never()).toApiDTO(any(Slot.class));
    }

    @DisplayName("Verifico che quando creo uno slot viene salvato correttamente")
    @Test
    void createSlot() {
        // ARRANGE
        Slot slot = new Slot();
        slot.setId(1L);
        slot.setDurata(10);

        SlotsApiDTO slotsApiDTO = new SlotsApiDTO();
        slotsApiDTO.setId(1L);
        slotsApiDTO.setDurata(10);

        when(slotMapper.toEntity(slotsApiDTO)).thenReturn(slot);
        when(slotRepository.save(slot)).thenReturn(slot);
        when(slotMapper.toApiDTO(slot)).thenReturn(slotsApiDTO);

        // ACT
        SlotsApiDTO createdSlot = slotService.createSlot(slotsApiDTO);

        // ASSERT
        assertNotNull(createdSlot);
        assertEquals(1L, createdSlot.getId());
        assertEquals(10, createdSlot.getDurata());
        verify(slotRepository, times(1)).save(slot);
        
    }

    @DisplayName( "Verifico che quando modifico uno slot viene salvato correttamente")
    @Test
    void updateSlot() {
        // ARRANGE
        Slot slot = new Slot();
        slot.setId(1L);
        slot.setDurata(10);

        SlotsApiDTO slotsApiDTO = new SlotsApiDTO();
        slotsApiDTO.setId(1L);
        slotsApiDTO.setDurata(10);
        
        when(slotMapper.toEntity(slotsApiDTO)).thenReturn(slot);
        when(slotRepository.save(slot)).thenReturn(slot);
        when(slotMapper.toApiDTO(slot)).thenReturn(slotsApiDTO);

        // ACT
        SlotsApiDTO updatedSlot = slotService.updateSlot(1L, slotsApiDTO);

        // ASSERT
        assertNotNull(updatedSlot);
        assertEquals(1L, updatedSlot.getId());
        assertEquals(10, updatedSlot.getDurata());
        verify(slotRepository, times(1)).save(slot);
    }

    @DisplayName("Verifico che quando elimino uno slot viene chiamato il metodo deleteById")
    @Test
    void deleteSlot() {
        // ARRANGE
        Long slotId = 1L;
        // ACT
        slotService.deleteSlot(slotId);
        // ASSERT
        verify(slotRepository, times(1)).deleteById(slotId);
    }
}
