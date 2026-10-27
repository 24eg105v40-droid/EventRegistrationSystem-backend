package com.college.eventregistration.config;

import com.college.eventregistration.entity.Event;
import com.college.eventregistration.entity.Registration;
import com.college.eventregistration.repository.EventRepository;
import com.college.eventregistration.repository.RegistrationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final EventRepository eventRepository;
    private final RegistrationRepository registrationRepository;

    public DataInitializer(EventRepository eventRepository, RegistrationRepository registrationRepository) {
        this.eventRepository = eventRepository;
        this.registrationRepository = registrationRepository;
    }

    @Override
    public void run(String... args) {
        if (eventRepository.count() > 0) {
            logger.info("Database already contains events. Skipping sample data initialization.");
            return;
        }

        logger.info("Initializing database with 10 sample college events and initial participants...");

        // 1. College Hackathon
        Event hackathon = new Event(
                "College Hackathon",
                LocalDate.now().plusDays(15),
                "Main Auditorium & Computing Hub",
                "A 24-hour sprint where student teams collaborate to build innovative software and hardware solutions addressing real-world problems. Mentors and industry judges will evaluate the submissions."
        );

        // 2. Coding Competition
        Event codingComp = new Event(
                "Coding Competition",
                LocalDate.now().plusDays(7),
                "Computer Lab 3, Department of CSE",
                "A fast-paced algorithmic problem-solving competition testing participants on data structures, dynamic programming, graph theory, and algorithmic complexity."
        );

        // 3. AI and Machine Learning Workshop
        Event aiWorkshop = new Event(
                "AI and Machine Learning Workshop",
                LocalDate.now().plusDays(10),
                "Seminar Hall B, Block 2",
                "Hands-on workshop covering the fundamentals of Deep Learning, PyTorch, model deployment, and practical applications in computer vision and natural language processing."
        );

        // 4. Web Development Workshop
        Event webDevWorkshop = new Event(
                "Web Development Workshop",
                LocalDate.now().plusDays(12),
                "MCA Lab 1",
                "Learn full-stack web development using modern technologies: React, RESTful APIs, Spring Boot, responsive CSS, and state management principles."
        );

        // 5. Project Exhibition
        Event projectExhibition = new Event(
                "Project Exhibition",
                LocalDate.now().plusDays(25),
                "Campus Central Courtyard",
                "Annual showcase where final-year engineering and science students present their capstone prototypes, IoT hardware, and software applications to visitors and evaluators."
        );

        // 6. Technical Symposium
        Event symposium = new Event(
                "Technical Symposium",
                LocalDate.now().plusDays(18),
                "Auditorium 2",
                "A platform for students to present peer-reviewed research papers, debate emerging tech policies, and interact with distinguished academic researchers."
        );

        // 7. Cultural Fest
        Event culturalFest = new Event(
                "Cultural Fest",
                LocalDate.now().plusDays(30),
                "Open Air Amphitheatre",
                "The grand annual intra-college cultural festival featuring musical performances, dance battles, dramatics, battle of bands, and art showcases."
        );

        // 8. Sports Meet
        Event sportsMeet = new Event(
                "Sports Meet",
                LocalDate.now().plusDays(20),
                "College Sports Complex & Track",
                "Inter-department sports tournament encompassing track & field, football, basketball, badminton, table tennis, and chess championships."
        );

        // 9. Entrepreneurship Seminar
        Event entrepreneurship = new Event(
                "Entrepreneurship Seminar",
                LocalDate.now().plusDays(22),
                "Management Block Conference Room",
                "Interactive session with successful startup founders and venture capitalists discussing business modeling, seed funding, product-market fit, and pitching."
        );

        // 10. Robotics Competition
        Event robotics = new Event(
                "Robotics Competition",
                LocalDate.now().plusDays(28),
                "Mechanical Engineering Workshop",
                "Exciting robotics arena contest featuring line-following bots, obstacle course navigators, and robo-soccer matches engineered by student teams."
        );

        List<Event> savedEvents = eventRepository.saveAll(List.of(
                hackathon, codingComp, aiWorkshop, webDevWorkshop, projectExhibition,
                symposium, culturalFest, sportsMeet, entrepreneurship, robotics
        ));

        // Seed realistic sample registrations to demonstrate participant list and counters
        registrationRepository.saveAll(List.of(
                new Registration("Aarav Sharma", "aarav.sharma@college.edu", savedEvents.get(0)),
                new Registration("Diya Patel", "diya.patel@college.edu", savedEvents.get(0)),
                new Registration("Rohan Verma", "rohan.verma@college.edu", savedEvents.get(0)),
                new Registration("Sneha Kulkarni", "sneha.k@college.edu", savedEvents.get(1)),
                new Registration("Vikram Singh", "vikram.s@college.edu", savedEvents.get(1)),
                new Registration("Ananya Iyer", "ananya.iyer@college.edu", savedEvents.get(2)),
                new Registration("Karthik Rao", "karthik.rao@college.edu", savedEvents.get(3)),
                new Registration("Meera Nair", "meera.nair@college.edu", savedEvents.get(3)),
                new Registration("Aditya Joshi", "aditya.j@college.edu", savedEvents.get(4)),
                new Registration("Pooja Reddy", "pooja.reddy@college.edu", savedEvents.get(6))
        ));

        logger.info("Successfully seeded 10 sample college events and sample participants.");
    }
}
