package com.garagesale.service;

import com.garagesale.domain.*;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.Category;
import com.garagesale.enums.OrderType;
import com.garagesale.exceptions.CardNotAvailableException;
import com.garagesale.exceptions.ProductAlreadyInCartException;
import com.garagesale.exceptions.ProductDoesntExistException;
import com.garagesale.mapping.OrderDTOMapping;
import com.garagesale.repository.AssetRepository;
import com.garagesale.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    private OrderServiceImpl orderService;
    @Mock
    private AssetRepository assetRepository;
    @Mock
    private AssetService assetService;
    @Mock
    private ValidatorService validatorService;

    Asset asset_1 = new Asset(1L, Category.LAPTOP,100, List.of(new Issue("none")),10);
    Asset asset_2 = new Asset(2L, Category.LAPTOP,100, List.of(new Issue("none")),0);
    Asset asset_3 = new Asset(3L,Category.LAPTOP,10,List.of(new Issue("none")),1);
    List<Asset> assetList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        orderService = new OrderServiceImpl(orderRepository,assetRepository,assetService,validatorService);
        assetList.add(asset_1);
        assetList.add(asset_3);
    }

    @Test
    void testFinalizeNORMALOrder(){
        Card card = new CreditCard("4263982640269299","Sergiu","316",30,10);
        OrderDTO orderDTO = new OrderDTO(card, OrderType.NORMAL,0,0, new int[]{1},"Sergiu","Sergiu");

        Mockito.when(assetService.findAllAvailable()).thenReturn(assetList);
        Mockito.when(validatorService.validateCardDetails(card)).thenReturn(true);
        Mockito.when(assetService.findById(1L)).thenReturn(asset_1);

        //Test order save
        orderService.finalizeOrder(orderDTO);
        ArgumentCaptor<PurchaseOrder> poArgumentCaptor = ArgumentCaptor.forClass(PurchaseOrder.class);
        verify(orderRepository).save(poArgumentCaptor.capture());

        PurchaseOrder capturedPO = poArgumentCaptor.getValue();
        assertThat(capturedPO).isEqualTo(OrderDTOMapping.dtoToOrder(orderDTO));

        //Test asset save
        ArgumentCaptor<Asset> assetArgumentCaptor = ArgumentCaptor.forClass(Asset.class);
        PurchaseOrder purchaseOrder = OrderDTOMapping.dtoToOrder(orderDTO);
        for(Asset asset: purchaseOrder.getAssets()) {
            verify(assetRepository).save(assetArgumentCaptor.capture());

            Asset capturedAsset = assetArgumentCaptor.getValue();
            assertThat(capturedAsset.getCategory()).isEqualTo(asset.getCategory());
        }

        PurchaseReceipt purchaseReceipt = OrderDTOMapping.dtoToPurchaseReceipt(orderDTO);

        assertEquals(purchaseReceipt.getCustomerName(), orderService.finalizeOrder(orderDTO).getCustomerName());
        assertEquals(purchaseReceipt.getCustomerEmail(), orderService.finalizeOrder(orderDTO).getCustomerEmail());
        assertEquals(purchaseReceipt.getPaymentDetails(), orderService.finalizeOrder(orderDTO).getPaymentDetails());
    }

    @Test
    void testFinalizeDiscountOrder_save(){
        Card card = new CreditCard("4263982640269299","Sergiu","316",30,10);
        OrderDTO orderDTO = new OrderDTO(card, OrderType.DISCOUNT,0,10, new int[]{1},"Sergiu","Sergiu");

        Mockito.when(assetService.findAllAvailable()).thenReturn(assetList);
        Mockito.when(validatorService.validateCardDetails(card)).thenReturn(true);
        Mockito.when(assetService.findById(1L)).thenReturn(asset_1);

        //Test order save
        orderService.finalizeOrder(orderDTO);
        ArgumentCaptor<PurchaseOrder> poArgumentCaptor = ArgumentCaptor.forClass(PurchaseOrder.class);
        verify(orderRepository).save(poArgumentCaptor.capture());

        PurchaseOrder capturedPO = poArgumentCaptor.getValue();
        assertThat(capturedPO).isEqualTo(OrderDTOMapping.dtoToOrder(orderDTO));

    }

    @Test
    void testFinalizeVoucherOrder_save(){
        Card card = new CreditCard("4263982640269299","Sergiu","316",30,10);
        OrderDTO orderDTO = new OrderDTO(card, OrderType.VOUCHER,50,0, new int[]{1},"Sergiu","Sergiu");

        Mockito.when(assetService.findAllAvailable()).thenReturn(assetList);
        Mockito.when(validatorService.validateCardDetails(card)).thenReturn(true);
        Mockito.when(assetService.findById(1L)).thenReturn(asset_1);

        //Test order save
        orderService.finalizeOrder(orderDTO);
        ArgumentCaptor<PurchaseOrder> poArgumentCaptor = ArgumentCaptor.forClass(PurchaseOrder.class);
        verify(orderRepository).save(poArgumentCaptor.capture());

        PurchaseOrder capturedPO = poArgumentCaptor.getValue();
        assertThat(capturedPO).isEqualTo(OrderDTOMapping.dtoToOrder(orderDTO));

    }


    @Test
    void testFinalizeOrder_throwErrorWhenNoAvailableAsset(){
        Card card = new CreditCard("4263982640269299","Sergiu","316",30,10);
        OrderDTO orderDTO = new OrderDTO(card, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu");
        Mockito.when(assetService.findAllAvailable()).thenReturn(Collections.emptyList());
        assertThatThrownBy(()-> orderService.finalizeOrder(orderDTO))
                .isInstanceOf(ProductDoesntExistException.class)
                .hasMessageContaining("No products available to buy");
    }
    @Test
    void testFinalizeOrder_ThrowErrorCardNotAvailable(){
        Card card = new CreditCard("4263982640269299","Sergiu","316",30,10);
        OrderDTO orderDTO = new OrderDTO(card, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu");
        Mockito.when(assetService.findAllAvailable()).thenReturn(assetList);
        Mockito.when(validatorService.validateCardDetails(card)).thenReturn(false);
        assertThatThrownBy(()-> orderService.finalizeOrder(orderDTO))
                .isInstanceOf(CardNotAvailableException.class)
                .hasMessageContaining("Card details are not good or expired");
    }

    @Test
    void testFinalizeOrder_ThrowErrorNoQuantity(){
        Card card = new CreditCard("4263982640269299","Sergiu","316",30,10);
        OrderDTO orderDTO = new OrderDTO(card, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu");
        Mockito.when(assetService.findAllAvailable()).thenReturn(assetList);
        Mockito.when(validatorService.validateCardDetails(card)).thenReturn(true);
        Mockito.when(assetService.findById(2L)).thenReturn(asset_2);

        assertThatThrownBy(()-> orderService.finalizeOrder(orderDTO))
                .isInstanceOf(ProductDoesntExistException.class)
                .hasMessageContaining("product with ID: " + orderDTO.getProductID()[0] + "  doesnt exist anymore");

    }

    @Test
    void testFinalizeOrder_ThrowErrorSameAssetCategory(){
        Card card = new CreditCard("4263982640269299","Sergiu","316",30,10);
        OrderDTO orderDTO = new OrderDTO(card, OrderType.NORMAL,0,0, new int[]{1,3},"Sergiu","Sergiu");
        Mockito.when(assetService.findAllAvailable()).thenReturn(assetList);
        Mockito.when(validatorService.validateCardDetails(card)).thenReturn(true);
        Mockito.when(assetService.findById(1L)).thenReturn(asset_1);
        Mockito.when(assetService.findById(3L)).thenReturn(asset_3);

        assertThatThrownBy(()-> orderService.finalizeOrder(orderDTO))
                .isInstanceOf(ProductAlreadyInCartException.class)
                .hasMessageContaining("You already have 1 item of type: " + asset_1.getCategory() + " in your cart.");

    }
    @Test
    void testFinalizeOrder_ThrowErrorInsufficientBalance(){
        Card card = new DebitCard("5610591081018250","Sergiu","316",30,10);
        OrderDTO orderDTO = new OrderDTO(card, OrderType.NORMAL,0,0, new int[]{1},"Sergiu","Sergiu");
        Mockito.when(assetService.findAllAvailable()).thenReturn(assetList);
        Mockito.when(validatorService.validateCardDetails(card)).thenReturn(true);
        Mockito.when(assetService.findById(1L)).thenReturn(asset_1);

        assertThatThrownBy(()-> orderService.finalizeOrder(orderDTO))
                .isInstanceOf(CardNotAvailableException.class)
                .hasMessageContaining("Insufficient balance");

    }


}
