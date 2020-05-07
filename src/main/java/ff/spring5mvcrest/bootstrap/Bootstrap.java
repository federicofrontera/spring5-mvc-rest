package ff.spring5mvcrest.bootstrap;

import ff.spring5mvcrest.domain.Category;
import ff.spring5mvcrest.domain.Customer;
import ff.spring5mvcrest.repositories.CategoryRepository;
import ff.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCategories();
        loadCustomers();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Categories loaded = " + categoryRepository.count());
    }

    private void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setId(Long.valueOf(1));
        customer1.setFirstname("John");
        customer1.setLastname("Smith");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(Long.valueOf(2));
        customer2.setFirstname("Michael");
        customer2.setLastname("Policy");
        customerRepository.save(customer2);

        System.out.println("Customers loaded = " + customerRepository.count());
    }
}
