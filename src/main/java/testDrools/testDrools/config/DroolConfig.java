package testDrools.testDrools.config;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class DroolConfig {

    private KieServices kieServices = KieServices.Factory.get();

    /*
    This method is responsible for creating a new KieFileSystem and adding a Drools rule file ("offer.drl") to it.
    It returns the configured KieFileSystem object.
     */
    private KieFileSystem getKieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource("offer.drl"));
        return kieFileSystem;

    }
    /*

    This method creates and configures a Drools KieContainer which is used to hold and manage compiled Drools rules.
    It calls the getKieFileSystem() method to obtain the configured KieFileSystem.
    It then creates a new KieBuilder using the obtained KieFileSystem and builds all the rules.
    The resulting KieModule is used to create a new KieContainer, and this container is returned.
     */
    @Bean
    public KieContainer getKieContainer() throws IOException {
        System.out.println("Container created...");
        getKieRepository();
        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem());
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kContainer;

    }
    /*
    This method retrieves the KieRepository from the KieServices and adds a new KieModule to it.
    The KieModule returned by the anonymous class has a getReleaseId() method that uses the default release ID from the repository.
    */
    private void getKieRepository() {

        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
    }
    /*
    This method creates and returns a new Drools KieSession, which is an entry point for interacting with the Drools rule engine.
    It calls the getKieContainer() method to obtain the configured KieContainer and then creates a new session from that container.
    */
    @Bean
    public KieSession getKieSession() throws IOException {
        System.out.println("session created...");
        return getKieContainer().newKieSession();

    }

}