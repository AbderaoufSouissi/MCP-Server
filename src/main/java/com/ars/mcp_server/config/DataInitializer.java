package com.ars.mcp_server.config;

import com.ars.mcp_server.entity.Product;
import com.ars.mcp_server.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            // Only initialize if database is empty
            if (repository.count() == 0) {
                log.info("Initializing products database with sample data...");

                List<Product> products = List.of(
                    // Laptops & Computers
                    Product.builder().name("Laptop Dell XPS 15").category("Electronics").price(1299.99).stock(15).build(),
                    Product.builder().name("MacBook Pro 14-inch M3").category("Electronics").price(1999.99).stock(12).build(),
                    Product.builder().name("ASUS ROG Gaming Laptop").category("Electronics").price(1599.99).stock(8).build(),
                    Product.builder().name("HP Pavilion 15").category("Electronics").price(749.99).stock(25).build(),
                    Product.builder().name("Microsoft Surface Laptop 5").category("Electronics").price(1299.99).stock(10).build(),
                    Product.builder().name("Lenovo ThinkPad X1 Carbon").category("Electronics").price(1449.99).stock(14).build(),
                    Product.builder().name("Acer Predator Helios 300").category("Electronics").price(1299.99).stock(9).build(),

                    // Smartphones & Tablets
                    Product.builder().name("iPhone 15 Pro").category("Electronics").price(999.99).stock(25).build(),
                    Product.builder().name("iPhone 15").category("Electronics").price(799.99).stock(35).build(),
                    Product.builder().name("Samsung Galaxy S24").category("Electronics").price(899.99).stock(30).build(),
                    Product.builder().name("Samsung Galaxy S24 Ultra").category("Electronics").price(1199.99).stock(18).build(),
                    Product.builder().name("Google Pixel 8 Pro").category("Electronics").price(899.99).stock(22).build(),
                    Product.builder().name("OnePlus 12").category("Electronics").price(799.99).stock(20).build(),
                    Product.builder().name("iPad Air").category("Electronics").price(599.99).stock(20).build(),
                    Product.builder().name("iPad Pro 12.9-inch").category("Electronics").price(1099.99).stock(15).build(),
                    Product.builder().name("Samsung Galaxy Tab S9").category("Electronics").price(749.99).stock(18).build(),

                    // Audio Equipment
                    Product.builder().name("Sony WH-1000XM5 Headphones").category("Electronics").price(399.99).stock(50).build(),
                    Product.builder().name("Apple AirPods Pro 2").category("Electronics").price(249.99).stock(60).build(),
                    Product.builder().name("Bose QuietComfort 45").category("Electronics").price(329.99).stock(40).build(),
                    Product.builder().name("JBL Flip 6 Speaker").category("Electronics").price(129.99).stock(75).build(),
                    Product.builder().name("Sonos One Speaker").category("Electronics").price(219.99).stock(35).build(),
                    Product.builder().name("Beats Studio Pro").category("Electronics").price(349.99).stock(30).build(),
                    Product.builder().name("Sennheiser HD 599").category("Electronics").price(199.99).stock(25).build(),

                    // Smart Home & IoT
                    Product.builder().name("Amazon Echo Dot 5th Gen").category("Electronics").price(49.99).stock(100).build(),
                    Product.builder().name("Google Nest Hub Max").category("Electronics").price(229.99).stock(40).build(),
                    Product.builder().name("Ring Video Doorbell Pro").category("Electronics").price(249.99).stock(35).build(),
                    Product.builder().name("Philips Hue Starter Kit").category("Electronics").price(199.99).stock(50).build(),
                    Product.builder().name("Nest Learning Thermostat").category("Electronics").price(249.99).stock(30).build(),
                    Product.builder().name("TP-Link Smart Plug 4-Pack").category("Electronics").price(29.99).stock(80).build(),

                    // Gaming
                    Product.builder().name("PlayStation 5").category("Electronics").price(499.99).stock(20).build(),
                    Product.builder().name("Xbox Series X").category("Electronics").price(499.99).stock(18).build(),
                    Product.builder().name("Nintendo Switch OLED").category("Electronics").price(349.99).stock(45).build(),
                    Product.builder().name("Meta Quest 3").category("Electronics").price(499.99).stock(25).build(),
                    Product.builder().name("Logitech G Pro Wireless Mouse").category("Electronics").price(149.99).stock(50).build(),
                    Product.builder().name("Razer BlackWidow V3 Keyboard").category("Electronics").price(139.99).stock(40).build(),
                    Product.builder().name("SteelSeries Arctis 7 Headset").category("Electronics").price(179.99).stock(35).build(),

                    // Cameras & Photography
                    Product.builder().name("Canon EOS R6 Mark II").category("Electronics").price(2499.99).stock(8).build(),
                    Product.builder().name("Sony Alpha A7 IV").category("Electronics").price(2499.99).stock(7).build(),
                    Product.builder().name("GoPro HERO 12 Black").category("Electronics").price(399.99).stock(30).build(),
                    Product.builder().name("DJI Mini 4 Pro Drone").category("Electronics").price(759.99).stock(15).build(),
                    Product.builder().name("Fujifilm Instax Mini 12").category("Electronics").price(79.99).stock(50).build(),

                    // Monitors & Displays
                    Product.builder().name("LG 27-inch 4K Monitor").category("Electronics").price(399.99).stock(25).build(),
                    Product.builder().name("Samsung Odyssey G7 Gaming Monitor").category("Electronics").price(699.99).stock(15).build(),
                    Product.builder().name("Dell UltraSharp 32-inch").category("Electronics").price(549.99).stock(20).build(),
                    Product.builder().name("ASUS ProArt Display").category("Electronics").price(899.99).stock(10).build(),

                    // Accessories & Peripherals
                    Product.builder().name("Logitech MX Master 3S Mouse").category("Electronics").price(99.99).stock(60).build(),
                    Product.builder().name("Apple Magic Keyboard").category("Electronics").price(149.99).stock(40).build(),
                    Product.builder().name("Anker PowerCore 20000mAh").category("Electronics").price(49.99).stock(100).build(),
                    Product.builder().name("SanDisk 1TB External SSD").category("Electronics").price(119.99).stock(55).build(),
                    Product.builder().name("Belkin USB-C Hub").category("Electronics").price(79.99).stock(70).build(),
                    Product.builder().name("Webcam Logitech C920").category("Electronics").price(79.99).stock(45).build(),
                    Product.builder().name("Blue Yeti USB Microphone").category("Electronics").price(129.99).stock(35).build(),

                    // Wearables
                    Product.builder().name("Apple Watch Series 9").category("Electronics").price(399.99).stock(40).build(),
                    Product.builder().name("Samsung Galaxy Watch 6").category("Electronics").price(299.99).stock(35).build(),
                    Product.builder().name("Fitbit Charge 6").category("Electronics").price(159.99).stock(50).build(),
                    Product.builder().name("Garmin Forerunner 255").category("Electronics").price(349.99).stock(25).build(),

                    // Books
                    Product.builder().name("The Great Gatsby").category("Books").price(12.99).stock(100).build(),
                    Product.builder().name("1984 by George Orwell").category("Books").price(14.99).stock(80).build(),
                    Product.builder().name("To Kill a Mockingbird").category("Books").price(13.50).stock(75).build(),
                    Product.builder().name("Harry Potter Collection").category("Books").price(89.99).stock(40).build(),
                    Product.builder().name("The Hobbit").category("Books").price(15.99).stock(60).build(),

                    // Clothing
                    Product.builder().name("Men's Cotton T-Shirt").category("Clothing").price(19.99).stock(200).build(),
                    Product.builder().name("Women's Jeans").category("Clothing").price(49.99).stock(150).build(),
                    Product.builder().name("Winter Jacket").category("Clothing").price(129.99).stock(45).build(),
                    Product.builder().name("Running Shoes").category("Clothing").price(79.99).stock(80).build(),
                    Product.builder().name("Summer Dress").category("Clothing").price(39.99).stock(100).build(),

                    // Appliances
                    Product.builder().name("Coffee Maker").category("Appliances").price(89.99).stock(35).build(),
                    Product.builder().name("Microwave Oven").category("Appliances").price(149.99).stock(25).build(),
                    Product.builder().name("Vacuum Cleaner").category("Appliances").price(199.99).stock(20).build(),
                    Product.builder().name("Blender").category("Appliances").price(59.99).stock(40).build(),
                    Product.builder().name("Air Fryer").category("Appliances").price(119.99).stock(30).build()
                );

                repository.saveAll(products);
                log.info("Successfully initialized {} products", products.size());
            } else {
                log.info("Database already contains {} products, skipping initialization", repository.count());
            }
        };
    }
}

