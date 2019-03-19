package com.medical.medicalsoftware.configuration;

import com.medical.medicalsoftware.model.Patient;
import com.medical.medicalsoftware.model.Staff;
import com.medical.medicalsoftware.repository.PatientRepository;
import com.medical.medicalsoftware.repository.StaffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = {PatientRepository.class, StaffRepository.class})
@Configuration
public class DbConfiguration {
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository, StaffRepository staffRepository){
        return new CommandLineRunner() {
                @Override
                public void run(String... args) throws Exception {
/*
                    patientRepository.deleteAll();
                    patientRepository.save(new Patient("Pop Ioan", "Waiting", "Stable", "With high priority", "Cluj-Napoca", "Kidney problems","Dialysis"));
                    patientRepository.save(new Patient("Nicula Mircea", "Processing", "In a coma", "Absolute emergency", "Cluj-Napoca", "Atrial fibrillation","Cardiorespiratory resuscitation"));
                    patientRepository.save(new Patient("Tanuc Ana", "Done", "Good condition", "Normal", "Cluj-Napoca", "Vertiginous syndrome","Stabilization medication"));
                    patientRepository.save(new Patient("Iustin Viorel", "Waiting", "Stable", "With high priority", "Cluj-Napoca", "Kidney problems","Dialysis"));
                    patientRepository.save(new Patient("Husan Maria", "Processing", "In a coma", "Absolute emergency", "Cluj-Napoca", "Atrial fibrillation","Cardiorespiratory resuscitation"));
                    patientRepository.save(new Patient("Zoran Nicoleta", "Done", "Good condition", "Normal", "Cluj-Napoca", "Vertiginous syndrome","Stabilization medication"));

                    staffRepository.deleteAll();
                    staffRepository.save(new Staff("raducrisan", "raducrisan", "Crisan Radu", "Doctor", "Cardiology", "2001"));
                    staffRepository.save(new Staff("anapascar", "anapascar", "Pascar Ana", "Doctor", "Imaging and medical radiology", "2014"));
                    staffRepository.save(new Staff("nicodobre", "nicodobre", "Dobre Nicoleta", "Nurse", "General", "2008"));
                    staffRepository.save(new Staff("mihaicretu", "mihaicretu", "Cretu Mihai", "Stretcher-bearer", "General", "2005"));
*/
                }
            };
    }


}
