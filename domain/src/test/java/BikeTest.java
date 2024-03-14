import com.bkool.domain.model.Bike;
import com.bkool.domain.model.BikeBuilder;
import com.bkool.domain.model.Item;
import com.bkool.domain.model.ItemBuilder;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;
import java.util.stream.Stream;

public class BikeTest {

    @Test
    public void testBike() {
        Bike bike = getBike();
        Bike bikeCopyRef = bike;
        Bike copyBike = getBike();
        List<Item> itemListDif = Stream.of(
                ItemBuilder.builder().model("Ultraflex").type("sillin").description("sillinAdaptable").build()
        ).toList();
        Bike copyBikeDifferentItems = BikeBuilder.builder().description("Bicicleta indoor con volante de inercia de 10 kg").price(129.00).manufacturer("Teseo").items(itemListDif).build();

        Assert.assertNotNull(bike);
        Assert.assertNotEquals(bike, copyBikeDifferentItems);
        Assert.assertNotEquals(bike.hashCode(), copyBikeDifferentItems.hashCode());
        Assert.assertEquals(bike, copyBike);
        Assert.assertEquals(bike.hashCode(), copyBike.hashCode());
        Assert.assertNotEquals(null, bike);
        Assert.assertNotEquals(bike, copyBikeDifferentItems);
        Assert.assertEquals(bike, bikeCopyRef);
        Assert.assertNotEquals(bike, BikeBuilder.builder().build());

    }

    @Test
    public void testItem() {
        Item item = getItem();
        Item itemCopyRef = item;
        Item copyItem = getItem();

        Assert.assertEquals(item, copyItem);
        Assert.assertEquals(item.hashCode(), copyItem.hashCode());
        Assert.assertNotEquals(null, item);
        Assert.assertEquals(item, itemCopyRef);
        Assert.assertNotEquals(item, ItemBuilder.builder().build());
    }


    @Test
    public void testBikeGetSetter() {
        Bike bike = getBike();
        Assert.assertEquals(bike.getId(),Long.valueOf(1L));
        Assert.assertEquals("Bicicleta indoor con volante de inercia de 10 kg", bike.getDescription());
        Assert.assertEquals(bike.getPrice(),Double.valueOf(129.00));
        Assert.assertEquals("DrumFit", bike.getName());
        Assert.assertEquals("Teseo", bike.getManufacturer());
        Assert.assertEquals(bike.getItems(),getItems());
    }

    @Test
    public void testItemGetSetter() {
        Item item = getItem();
        Assert.assertEquals(item.getId(),Long.valueOf(1L));
        Assert.assertEquals("Extreme",item.getModel());
        Assert.assertEquals("pedales",item.getType());
        Assert.assertEquals("Repuesto compatible con Extreme/Extreme 20/Extreme 25/Ultraflex 25/Poweractive",item.getDescription());
    }

    private Bike getBike(){
        return BikeBuilder.builder().id(1L).name("DrumFit").description("Bicicleta indoor con volante de inercia de 10 kg").price(129.00).manufacturer("Teseo").items(getItems()).build();
    }
    private List<Item> getItems(){
        return Stream.of(getItem()).toList();
    }
    private Item getItem(){
        return  ItemBuilder.builder().id(1L).model("Extreme").type("pedales").description("Repuesto compatible con Extreme/Extreme 20/Extreme 25/Ultraflex 25/Poweractive").build();
    }
}
