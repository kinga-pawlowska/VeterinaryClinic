package com.kingapawlowska.klinikaweterynaryjna.bootstrap;

import com.kingapawlowska.klinikaweterynaryjna.model.*;
import com.kingapawlowska.klinikaweterynaryjna.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

//    @Autowired - for 4.2 Spring version
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                      SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        /***************************************************************************************************************
         * Pet Types
         **************************************************************************************************************/
        // -------------------------------------------------------------------------------------------------------------
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);
        // -------------------------------------------------------------------------------------------------------------
        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);
        // -------------------------------------------------------------------------------------------------------------
        PetType fox = new PetType();
        fox.setName("Fox");
        PetType savedFoxPetType = petTypeService.save(fox);
        // -------------------------------------------------------------------------------------------------------------
        PetType lynx = new PetType();
        lynx.setName("Lynx");
        PetType savedLynxPetType = petTypeService.save(lynx);
        // -------------------------------------------------------------------------------------------------------------
        PetType fish = new PetType();
        fish.setName("Fish");
        PetType savedFishPetType = petTypeService.save(fish);
        // -------------------------------------------------------------------------------------------------------------
        PetType rabbit = new PetType();
        rabbit.setName("Rabbit");
        PetType savedRabbitPetType = petTypeService.save(rabbit);
        // -------------------------------------------------------------------------------------------------------------
        PetType hamster = new PetType();
        hamster.setName("Hamster");
        PetType savedHamsterPetType = petTypeService.save(hamster);
        // -------------------------------------------------------------------------------------------------------------
        PetType rat = new PetType();
        rat.setName("Rat");
        PetType savedRatPetType = petTypeService.save(rat);
        // -------------------------------------------------------------------------------------------------------------
        PetType guineaPig = new PetType();
        guineaPig.setName("Guinea Pig");
        PetType savedGuineaPigPetType = petTypeService.save(guineaPig);
        // -------------------------------------------------------------------------------------------------------------
        PetType turtle = new PetType();
        turtle.setName("Turtle");
        PetType savedTurtlePetType = petTypeService.save(turtle);
        // -------------------------------------------------------------------------------------------------------------
        PetType parrot = new PetType();
        parrot.setName("Parrot");
        PetType savedParrotPetType = petTypeService.save(parrot);
        // -------------------------------------------------------------------------------------------------------------
        PetType hedgehog = new PetType();
        hedgehog.setName("Hedgehog");
        PetType savedHedgehogPetType = petTypeService.save(hedgehog);
        // -------------------------------------------------------------------------------------------------------------
        PetType iguana = new PetType();
        iguana.setName("Iguana");
        PetType savedIguanaPetType = petTypeService.save(iguana);
        // -------------------------------------------------------------------------------------------------------------
        PetType other = new PetType();
        other.setName("OTHER");
        PetType savedOtherPetType = petTypeService.save(other);
        // -------------------------------------------------------------------------------------------------------------

        /***************************************************************************************************************
         * Vet Specialities
         **************************************************************************************************************/
        // -------------------------------------------------------------------------------------------------------------
        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);
        // -------------------------------------------------------------------------------------------------------------
        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);
        // -------------------------------------------------------------------------------------------------------------
        Speciality generalVeterinaryMedicine = new Speciality();
        generalVeterinaryMedicine.setDescription("General veterinary medicine");
        Speciality savedGeneralVeterinaryMedicine = specialityService.save(generalVeterinaryMedicine);
        // -------------------------------------------------------------------------------------------------------------
        Speciality ophthalmology = new Speciality();
        ophthalmology.setDescription("Ophthalmology");
        Speciality savedOphthalmology = specialityService.save(ophthalmology);
        // -------------------------------------------------------------------------------------------------------------
        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);
        // -------------------------------------------------------------------------------------------------------------
        Speciality orthopedics = new Speciality();
        orthopedics.setDescription("Orthopedics");
        Speciality savedOrthopedics = specialityService.save(orthopedics);
        // -------------------------------------------------------------------------------------------------------------
        Speciality cardiology = new Speciality();
        cardiology.setDescription("Cardiology");
        Speciality savedCardiology = specialityService.save(cardiology);
        // -------------------------------------------------------------------------------------------------------------
        Speciality obstetrics = new Speciality();
        obstetrics.setDescription("Obstetrics");
        Speciality savedObstetrics = specialityService.save(obstetrics);
        // -------------------------------------------------------------------------------------------------------------
        Speciality neurology = new Speciality();
        neurology.setDescription("Neurology");
        Speciality savedNeurology = specialityService.save(neurology);
        // -------------------------------------------------------------------------------------------------------------
        Speciality dermatology = new Speciality();
        dermatology.setDescription("Dermatology");
        Speciality savedDermatology = specialityService.save(dermatology);
        // -------------------------------------------------------------------------------------------------------------
        Speciality rehabilitation = new Speciality();
        rehabilitation.setDescription("Rehabilitation");
        Speciality savedRehabilitation = specialityService.save(rehabilitation);
        // -------------------------------------------------------------------------------------------------------------
        // -------------------------------------------------------------------------------------------------------------

        /***************************************************************************************************************
         * Vets
         **************************************************************************************************************/
        // -------------------------------------------------------------------------------------------------------------
        Vet vet1 = new Vet();
        vet1.setFirstName("Markus");
        vet1.setLastName("Watkins");
        vet1.getSpecialities().add(generalVeterinaryMedicine);
        vetService.save(vet1);
        // -------------------------------------------------------------------------------------------------------------
        Vet vet2 = new Vet();
        vet2.setFirstName("Brylee");
        vet2.setLastName("Gentry");
        vet2.getSpecialities().add(savedRadiology);
        vetService.save(vet2);
        // -------------------------------------------------------------------------------------------------------------
        Vet vet3 = new Vet();
        vet3.setFirstName("Bernard");
        vet3.setLastName("Salinas");
        vet3.getSpecialities().add(savedDentistry);
        vetService.save(vet3);
        // -------------------------------------------------------------------------------------------------------------
        Vet vet4 = new Vet();
        vet4.setFirstName("Jacquelyn");
        vet4.setLastName("Carney");
        vet4.getSpecialities().add(savedOphthalmology);
        vetService.save(vet4);
        // -------------------------------------------------------------------------------------------------------------
        Vet vet5 = new Vet();
        vet5.setFirstName("Vivian");
        vet5.setLastName("Barnes");
        vet5.getSpecialities().add(savedSurgery);
        vetService.save(vet5);
        // -------------------------------------------------------------------------------------------------------------
        Vet vet6 = new Vet();
        vet6.setFirstName("Caden");
        vet6.setLastName("Ferguson");
        vet6.getSpecialities().add(savedOrthopedics);
        vetService.save(vet6);
        // -------------------------------------------------------------------------------------------------------------
        Vet vet7 = new Vet();
        vet7.setFirstName("Rose");
        vet7.setLastName("Baldwin");
        vet7.getSpecialities().add(savedCardiology);
        vetService.save(vet7);
        // -------------------------------------------------------------------------------------------------------------
        Vet vet8 = new Vet();
        vet8.setFirstName("Tatiana");
        vet8.setLastName("Leblanc");
        vet8.getSpecialities().add(savedObstetrics);
        vetService.save(vet8);
        // -------------------------------------------------------------------------------------------------------------
        Vet vet9 = new Vet();
        vet9.setFirstName("Chace");
        vet9.setLastName("Gibson");
        vet9.getSpecialities().add(savedNeurology);
        vetService.save(vet9);
        // -------------------------------------------------------------------------------------------------------------
        Vet vet10 = new Vet();
        vet10.setFirstName("Kathryn");
        vet10.setLastName("Galvan");
        vet10.getSpecialities().add(savedDermatology);
        vetService.save(vet10);
        // -------------------------------------------------------------------------------------------------------------
        Vet vet11 = new Vet();
        vet11.setFirstName("Lacey");
        vet11.setLastName("Rogers");
        vet11.getSpecialities().add(savedRehabilitation);
        vetService.save(vet11);
        // -------------------------------------------------------------------------------------------------------------

        /***************************************************************************************************************
         * Owners and Pets
         **************************************************************************************************************/
        // -------------------------------------------------------------------------------------------------------------
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("123 Brickerel");
        owner1.setCity("London");
        owner1.setTelephone("1231231234");

        Pet owner1Pet1 = new Pet();
        owner1Pet1.setPetType(savedDogPetType);
        owner1Pet1.setOwner(owner1);
        owner1Pet1.setBirthDate(LocalDate.now());
        owner1Pet1.setName("Rosco");
        owner1.getPets().add(owner1Pet1);
        ownerService.save(owner1);
        // -------------------------------------------------------------------------------------------------------------
        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("123 Brickerel");
        owner2.setCity("Birmingham");
        owner2.setTelephone("1231231234");

        Pet owner2Pet1 = new Pet();
        owner2Pet1.setName("Jager");
        owner2Pet1.setOwner(owner2);
        owner2Pet1.setBirthDate(LocalDate.now());
        owner2Pet1.setPetType(savedCatPetType);
        owner2.getPets().add(owner2Pet1);
        ownerService.save(owner2);
        // -------------------------------------------------------------------------------------------------------------
        Owner owner3 = new Owner();
        owner3.setFirstName("Annika");
        owner3.setLastName("Santiago");
        owner3.setAddress("123 Brickerel");
        owner3.setCity("Leeds");
        owner3.setTelephone("1231231234");

        Pet owner3Pet1 = new Pet();
        owner3Pet1.setName("Vice");
        owner3Pet1.setOwner(owner3);
        owner3Pet1.setBirthDate(LocalDate.now());
        owner3Pet1.setPetType(savedFoxPetType);
        owner3.getPets().add(owner3Pet1);
        ownerService.save(owner3);
        // -------------------------------------------------------------------------------------------------------------
        Owner owner4 = new Owner();
        owner4.setFirstName("Journey");
        owner4.setLastName("Lynn");
        owner4.setAddress("123 Brickerel");
        owner4.setCity("Glasgow");
        owner4.setTelephone("1231231234");

        Pet owner4Pet1 = new Pet();
        owner4Pet1.setName("Barkley");
        owner4Pet1.setOwner(owner4);
        owner4Pet1.setBirthDate(LocalDate.now());
        owner4Pet1.setPetType(savedParrotPetType);
        owner4.getPets().add(owner4Pet1);
        ownerService.save(owner4);
        // -------------------------------------------------------------------------------------------------------------
        Owner owner5 = new Owner();
        owner5.setFirstName("Grayson");
        owner5.setLastName("Garrison");
        owner5.setAddress("123 Brickerel");
        owner5.setCity("\tSheffield");
        owner5.setTelephone("1231231234");

        Pet owner5Pet1 = new Pet();
        owner5Pet1.setName("Lurch");
        owner5Pet1.setOwner(owner5);
        owner5Pet1.setBirthDate(LocalDate.now());
        owner5Pet1.setPetType(savedDogPetType);
        owner5.getPets().add(owner5Pet1);
        ownerService.save(owner5);

        /***************************************************************************************************************
         * Visits
         **************************************************************************************************************/
         //-------------------------------------------------------------------------------------------------------------
        Visit owner1Pet1Visit = new Visit();
        owner1Pet1Visit.setPet(owner1Pet1);
        owner1Pet1Visit.setDate(LocalDate.now());
        owner1Pet1Visit.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
        visitService.save(owner1Pet1Visit);
        // -------------------------------------------------------------------------------------------------------------
        Visit owner2Pet1Visit = new Visit();
        owner2Pet1Visit.setPet(owner2Pet1);
        owner2Pet1Visit.setDate(LocalDate.now());
        owner2Pet1Visit.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
        visitService.save(owner2Pet1Visit);
        // -------------------------------------------------------------------------------------------------------------
        Visit owner3Pet1Visit = new Visit();
        owner3Pet1Visit.setPet(owner3Pet1);
        owner3Pet1Visit.setDate(LocalDate.now());
        owner3Pet1Visit.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
        visitService.save(owner3Pet1Visit);
        // -------------------------------------------------------------------------------------------------------------
        Visit owner4Pet1Visit = new Visit();
        owner4Pet1Visit.setPet(owner4Pet1);
        owner4Pet1Visit.setDate(LocalDate.now());
        owner4Pet1Visit.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. ");
        visitService.save(owner1Pet1Visit);
        // -------------------------------------------------------------------------------------------------------------
        System.out.println("Loading Owners, Pets, Specialities and Vets");
    }
}
