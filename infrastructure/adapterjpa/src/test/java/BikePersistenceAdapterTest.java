import com.bkool.domain.model.Bike;
import com.bkool.domain.model.BikeBuilder;
import com.bkool.domain.model.Item;
import com.bkool.domain.model.ItemBuilder;
import com.bkool.infrastructure.entities.BikeEntity;
import com.bkool.infrastructure.mapper.BikeMapper;
import com.bkool.infrastructure.ports.BikePersistenceAdapter;
import com.bkool.infrastructure.repositories.BikeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class BikePersistenceAdapterTest {

    @Mock
    private BikeRepository bikeRepository;

    @InjectMocks
    private BikePersistenceAdapter bikePersistenceAdapter;


    @Test
    public void validate_save() {
        Bike bikeToSave = getBike();
        BikeEntity bikeEntity = BikeMapper.toEntity(bikeToSave);
        when(bikeRepository.save(any(BikeEntity.class))).thenReturn(bikeEntity);
        Bike bike = bikePersistenceAdapter.save(bikeToSave);
        Assert.assertNotNull(bike);
        verify(bikeRepository, atLeastOnce()).save(any(BikeEntity.class));
    }

    @Test
    public void validate_save_null() {
        Bike bike = bikePersistenceAdapter.save(null);
        Assert.assertNull(bike);
        verify(bikeRepository, VerificationModeFactory.atLeast(0)).save(any(BikeEntity.class));
    }
    @Test
    public void validate_search_null_fields() {
        List<Bike> bikes = bikePersistenceAdapter.search(null, null, null, null);
        Assert.assertEquals(Collections.EMPTY_LIST, bikes);
        verify(bikeRepository, VerificationModeFactory.atLeast(0)).findByItemsIn(null,null,null,null);
    }

    @Test
    public void validate_search() {
        Sort.Direction direction = Sort.Direction.valueOf(Optional.ofNullable("ASC").orElse("asc").toUpperCase());
        List<BikeEntity> list = Collections.singletonList(BikeMapper.toEntity(getBike()));
        when(bikeRepository.findByItemsIn("name", "manufacturer", "item",  Sort.by(direction, "name"))).thenReturn(list);
        List<Bike> bikes = bikePersistenceAdapter.search("name", "manufacturer", "item", "ASC");

        Assert.assertNotNull(bikes);
        verify(bikeRepository, atLeastOnce()).findByItemsIn("name", "manufacturer", "item",  Sort.by(direction, "name"));
    }

    private Bike getBike() {
        return BikeBuilder.builder().id(1L).name("DrumFit").description("Bicicleta indoor con volante de inercia de 10 kg").price(129.00).manufacturer("Teseo").items(getItems()).build();
    }

    private List<Item> getItems() {
        return Stream.of(getItem()).toList();
    }

    private Item getItem() {
        return ItemBuilder.builder().id(1L).model("Extreme").type("pedales").description("Repuesto compatible con Extreme/Extreme 20/Extreme 25/Ultraflex 25/Poweractive").build();
    }
}
