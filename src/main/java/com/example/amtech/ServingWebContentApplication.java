package com.example.amtech;

import com.example.amtech.models.Category;
import com.example.amtech.models.CategoryService;
import com.example.amtech.models.ProductService;
import com.example.amtech.repository.CategoryRepository;
import com.example.amtech.repository.CustomProductRepository;
import com.example.amtech.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableMongoRepositories
public class ServingWebContentApplication extends SpringBootServletInitializer implements CommandLineRunner {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServingWebContentApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

    @Configuration
    public static class DirectoryExposer implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(final ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/images/product/**").addResourceLocations("file:images/product/");
        }
    }

    // TODO remove when project is finished
    @Autowired ProductRepository productRepo;
    @Autowired CategoryRepository categoryRepository;
    @Autowired CustomProductRepository customProductRepo;

    public void run(String... args) {
        ProductService productService = new ProductService(productRepo, customProductRepo);
        CategoryService categoryService = new CategoryService(categoryRepository);

        // Clean up any previous data
        productService.deleteAllProduct(); // Doesn't delete the collection
        categoryService.deleteAllCategories();

        System.out.println("-------------CREATE CATEGORIES-------------------------------\n");
        try {
            categoryService.createCategory(new Category("1", "cameras"));
            categoryService.createCategory(new Category("2", "headsets"));
            categoryService.createCategory(new Category("3", "laptops"));
            categoryService.createCategory(new Category("4", "smartphones"));
            categoryService.createCategory(new Category("5", "tablets"));
            categoryService.createCategory(new Category("6", "tvs"));
            categoryService.createCategory(new Category("7", "videogames"));
            categoryService.createCategory(new Category("8", "drones"));
            categoryService.createCategory(new Category("9", "printers"));
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        System.out.println(categoryService.count() + " categories created");

        System.out.println("-------------CREATE PRODUCT ITEMS-------------------------------\n");
        productService.createProduct("1", "tvs/1.jpeg", "SAMSUNG 75\" Class QLED 4K", "Enjoy ultra-intense 4K vivid color and sharpened clarity with the Q60A / Q60AB. It combines Quantum Dot Technology with the power of 100% Color Volume* to deliver a billion of shades for colorful, razor-sharp visuals. The ultra-smart Quantum Processor 4K Lite automatically upscales and transforms your content into 4K. Dual LED** backlighting adjusts and coordinates with content in real time to enhance contrast and detail. Plus, with the rechargeable SolarCell Remote, you can easily access and control Smart TV and all your connected devices.", 1097.99, 10,3, false, 0, new String[]{"tvs"});
        productService.createProduct("2", "tvs/2.jpeg", "LG 55\" Class 4K UHD 2160P Smart TV", "Real 4K clarity. A full display for fuller entertainment. It's Ultra High Definition in a real 4K display for four times the resolution of Full HD. A powerful processor enhances color, contrast, and clarity, while webOS and LG Channels deliver a huge library of content right to your fingertips. There' more to love about your TV. LG UHD checks off your wishlist of features without the price tag. Ultra High Definition displays detail the 4K resolution you demand. Picture quality delivers on color, contrast and clarity. Smart features open more control to your home and entertainment. The LG 55\" 4K UHD Smart UN6955 TV features beautiful entertainment at a beautiful price.", 448.00, 10,3, false, 0, new String[]{"tvs"});
        productService.createProduct("3", "tvs/3.jpeg", "TCL 65\" Class 6-Series 4K UHD ", "Enjoy ultra-intense 4K vivid color and sharpened clarity with the Q60A / Q60AB. It combines Quantum Dot Technology with the power of 100% Color Volume* to deliver a billion of shades for colorful, razor-sharp visuals. The ultra-smart Quantum Processor 4K Lite automatically upscales and transforms your content into 4K. Dual LED** backlighting adjusts and coordinates with content in real time to enhance contrast and detail. Plus, with the rechargeable SolarCell Remote, you can easily access and control Smart TV and all your connected devices.", 1097.99, 10,3, false, 0, new String[]{"tvs"});
        productService.createProduct("4", "laptops/1.jpeg", "Microsoft Surface Laptop 3, 15\" Touch-Screen", "Make a powerful statement and fuel your ideas with new Surface Surface Laptop 3. Sleek and light, with improved speed, performance, typing comfort, and battery life, it travels with ease and makes every day more productive. And whether you’re creating, catching up or chilling out, Surface Surface Laptop 3 is ready when you are with more power, Fast Charging, the latest processors, greater multitasking performance, all-day battery, and Instant On. Bring your vision to life on the vibrant PixelSense Display, now available in a new, larger 15” touchscreen. Listen to the improved Omnisonic Speakers, discreetly hidden below the keyboard.", 1099.99, 10,5, false, 0, new String[]{"laptops"});
        productService.createProduct("5", "laptops/2.jpeg", "Apple Macbook Pro 15.4\" Laptop", "Apple sucks", 3999.99, 0,1, false, 0, new String[]{"laptops"});
        productService.createProduct("6", "laptops/3.jpeg", "Lenovo Ideapad Flex 5 14\" FHD", "The new Lenovo™ IdeaPad™ Flex 5 14 offers more ways to connect, interact, and immerse yourself with a powerful combination of performance, connectivity, and entertainment. Take advantage of the latest AMD Ryzen 3 5300U processors and blazing SSD storage, along with crisp visuals on the FHD display, Dolby Audio™ DAX3 speakers, all while connected to WiFi.", 429.00, 10,2, false, 0, new String[]{"laptops"});
        productService.createProduct("7", "laptops/4.jpeg", "HP Spectre x360 15.6\" 4K UHD Touchscreen 2-in-1 Laptop", "HP's most powerful Spectre convertible yet looks stunning and runs smooth with a jaw-dropping high definition, near-borderless display and long battery life that takes you from day to night. Add in the security features you need for peace of mind and this stylish powerhouse has everything you need to power through.", 1399.99, 10,4, false, 0, new String[]{"laptops"});
        productService.createProduct("8", "cameras/1.jpeg", "FUJIFILM Instax Mini 7+ Purple Bundle", "The new INSTAX Mini 7+ instant camera is stylish, colorful and compact. With its exposure control adjustment and easy point-and-shoot features, the INSTAX Mini 7+ will no doubt quickly become a favorite. With a high-quality FUJINON lens in combination with the INSTAX Mini Film, superior images are just an instant click away. Just point and shoot and let the fun start immediately. The new INSTAX Mini 7+ instant camera is available in five trending colors: Lavender, Light Blue, Light Pink, Seafoam Green and Coral.", 48.00, 10,4, false, 0, new String[]{"cameras"});
        productService.createProduct("9", "cameras/2.jpeg", "Canon EOS Rebel T100 Digital ", "Creating distinctive stories with DSLR quality photos and Full HD movies is easier than you think with the 18 Megapixel Canon EOS Rebel T100. Share instantly and shoot remotely via your compatible smartphone with Wi-Fi and the Canon Camera Connect app. ", 349.00, 10,4, false, 0, new String[]{"cameras"});
        productService.createProduct("10", "cameras/3.jpeg", "GoPro HERO8 Black", "The GoPro HERO8 Black is the most versatile, unshakable HERO camera ever. The reimagined shape is more pocketable, and folding fingers at the base let you swap mounts quickly.", 269.00, 10,4, false, 0, new String[]{"cameras"});
        productService.createProduct("11", "smartphones/1.jpeg", "iPhone 13 128GB Midnight", "Apple sucks 2.0", 1399.00, 10,1, false, 0, new String[]{"smartphones"});
        productService.createProduct("12", "smartphones/2.jpeg", "SAMSUNG Galaxy S20", "Description Create anywhere on the sleek, powerful Galaxy S20 5G with an expansive 6.2\" Infinity-O display and radical new 30X Space Zoom.1 Single Take AI Capture video and multiple types of images with one tap of the shutter button. ", 995.39, 10,4, false, 0, new String[]{"smartphones"});
        productService.createProduct("13", "smartphones/3.jpeg", "Samsung Note 20 5G", "The innovative SAMSUNG Galaxy Note20 5G in Mystic Gray from Straight Talk is ready for business. Use the ultra-responsive S Pen in SAMSUNG Notes to keep track of every great idea you have. The Hyper Fast processor means you can multitask to no end, and the long-lasting, Super-Fast Charge can give you hours of juice in a matter of minutes.", 999.99, 0,2, false, 0, new String[]{"smartphones"});
        productService.createProduct("14", "smartphones/4.jpeg", "Huawei P20 Pro", "HUAWEI's P series has always been a pioneer of smartphone photography. Now the HUAWEI P20 Pro is once again leading the way with the revolutionary Leica Triple Camera, where aesthetic vision meets an advanced camera system that shines a light on intelligent photography.", 603.00, 10,4, false, 0, new String[]{"smartphones"});
        productService.createProduct("15", "videogames/1.jpeg", "Elden Ring, Bandai Namco, PlayStation 5", "The Golden Order has been broken. Rise, Tarnished, and be guided by grace ti brandish the power of the Elden Ring and become an Elden Lord in the Lands Between. ELDEN RING, developed by FromSoftware Inc. and produced by BANDAI NAMCO Entertainment Inc., is a fantasy action-RPG and FromSoftware's largest game to date, set withing a world full of mystery and peril.", 59.99, 1,5, false, 0, new String[]{"videogames"});
        productService.createProduct("16", "cameras/4.jpeg", "Nikon 1 V2", "Why compromise when you want to travel light? The Nikon 1 V2 ensures you don’t have to.\nWith a 14.2-megapixel super high-speed AF CMOS sensor, high ISO, and direct access to manual controls, you get detailed images just the way you want them even in low light.\n" + "Combining speed, power, and precision in a seriously small body, this is a versatile main camera and a great second camera that you’ll end up putting first more often than you’d expect.", 999.99, 3,2, false, 0, new String[]{"cameras"});
        productService.createProduct("17", "drones/1.jpeg", "DJI Phantom 3", "Take to the sky and capture your world in crisp 4K HD with the DJI Phantom 3 Professional Drone. This incredible piece can take aerial photos to bring your imagination to life. The DJI Phantom three drone is a fully integrated package with an intelligent flight system and remote control for easy use. The DJI Phantom 3 Drone features an integrated, stabilized camera, with12 a megapixels photo camera and an integrated 3-axis stabilization gimbal that provides you with a live HD view. The camera can also capture breathtaking 4K video. The HD drone is equipped with vision positioning for indoor flight, so you can capture the moment indoors or outdoors. A powerful mobile app with an auto video editor makes it easy to create videos of your adventures.", 603.00, 10,4, false, 0, new String[]{"drones"});
        productService.createProduct("18", "headsets/1.jpeg", "Sony WH1000XM4", "Sony’s intelligent industry-leading noise cancelling headphones with premium sound elevate your listening experience with the ability to personalize and control everything you hear. Get up to 30 hours of battery life with quick charging capabilities, enjoy an enhanced Smart Listening feature set, and carry conversations hands-free with speak-to-chat. Industry-leading noise cancellation technology means you hear every word, note, and tune with incredible clarity, no matter your environment. These noise cancelling headphones feature additional microphones that assist in isolating sound for a reduction of even more high and mid frequency sounds. The WH-1000XM4 headphones use an array of smart technologies to create a seamless, hands-free listening experience.", 59.99, 1,5, false, 0, new String[]{"headsets"});

        System.out.println(productService.count() + " products created");
    }
}
