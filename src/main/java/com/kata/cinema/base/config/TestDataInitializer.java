package com.kata.cinema.base.config;

import com.kata.cinema.base.exception.CollectionNotFoundException;
import com.kata.cinema.base.exception.MovieNotFoundException;
import com.kata.cinema.base.models.dto.request.MovieRequestDto;
import com.kata.cinema.base.models.dto.request.ReviewRequestDto;
import com.kata.cinema.base.models.entitys.Collection;
import com.kata.cinema.base.models.entitys.FolderMovie;
import com.kata.cinema.base.models.entitys.Genre;
import com.kata.cinema.base.models.entitys.Media;
import com.kata.cinema.base.models.entitys.Person;
import com.kata.cinema.base.models.entitys.Profession;
import com.kata.cinema.base.models.entitys.Review;
import com.kata.cinema.base.models.entitys.Role;
import com.kata.cinema.base.models.entitys.Score;
import com.kata.cinema.base.models.entitys.User;
import com.kata.cinema.base.models.enums.MPAA;
import com.kata.cinema.base.models.enums.Privacy;
import com.kata.cinema.base.models.enums.RARS;
import com.kata.cinema.base.models.enums.TypeReview;
import com.kata.cinema.base.service.entity.CollectionService;
import com.kata.cinema.base.service.entity.GenreService;
import com.kata.cinema.base.service.entity.MediaService;
import com.kata.cinema.base.service.entity.ReviewService;
import com.kata.cinema.base.service.entity.RoleService;
import com.kata.cinema.base.service.entity.UserService;
import com.kata.cinema.base.service.entity.ScoreService;
import com.kata.cinema.base.service.entity.FolderService;
import com.kata.cinema.base.service.entity.PersonService;
import com.kata.cinema.base.service.entity.ProfessionService;
import com.kata.cinema.base.service.entity.MovieService;
import com.kata.cinema.base.models.enums.FolderMovieType;
import com.kata.cinema.base.models.enums.RoleNameEnum;
import com.kata.cinema.base.models.entitys.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


@Component
@ConditionalOnExpression("${run.init:true}")
@RequiredArgsConstructor
public class TestDataInitializer {
    private final RoleNameEnum[] roles = RoleNameEnum.values();
    private final FolderMovieType[] folderTypes = FolderMovieType.values();
    private final UserService userService;
    private final RoleService roleService;
    private final FolderService folderService;
    private final PasswordEncoder passwordEncoder;
    private final PersonService personService;
    private final ProfessionService professionService;
    private final MovieService movieService;
    private final ScoreService scoreService;
    private final MediaService mediaService;
    private final ReviewService reviewService;
    private final GenreService genreService;
    private final CollectionService collectionService;

    @EventListener(ApplicationReadyEvent.class)
    @Order(1)
    public void initRoles() {
        for (RoleNameEnum role : roles) {
            if (roleService.findByName(role) == null) {
                Role newRole = new Role();
                newRole.setName(role);
                roleService.save(newRole);
            }
        }
    }

    @Order(2)
    @EventListener(ApplicationReadyEvent.class)
    public void initUsers() {
        Random random = new Random();
        int userNum = 1;
        for (int i = 0; i < 25; i++) {
            String email = "user" + userNum + "@mail.ru";
            String firstName = "Имя" + userNum;
            String lastName = "Фамилия" + userNum;
            String password = "password";
            LocalDate birthday = LocalDate.of(random.nextInt(41) + 1970, Month.JANUARY, 1)
                    .plusDays(random.nextInt(365))
                    .plusYears(random.nextInt(41));
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.findByName(RoleNameEnum.USER));
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(firstName);
            newUser.setLastName(lastName);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setBirthday(birthday);
            newUser.setRoles(roles);
            userService.save(newUser);
            initFolders(newUser.getEmail());
            userNum++;
        }
        // инициализация администратора
        String email = "admin@gmail.com";
        String firstName = "admin";
        String lastName = "admin";
        String password = "admin";
        LocalDate birthday = LocalDate.of(random.nextInt(41) + 1970, Month.JANUARY, 1)
                .plusDays(random.nextInt(365))
                .plusYears(random.nextInt(41));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(roleService.findByName(RoleNameEnum.USER));
        adminRoles.add(roleService.findByName(RoleNameEnum.ADMIN));
        User newAdmin = new User();
        newAdmin.setEmail(email);
        newAdmin.setName(firstName);
        newAdmin.setLastName(lastName);
        newAdmin.setPassword(passwordEncoder.encode(password));
        newAdmin.setBirthday(birthday);
        newAdmin.setRoles(adminRoles);
        userService.update(newAdmin);
        initFolders(newAdmin.getEmail());
        // инициализация издателя
        email = "publicist@gmail.com";
        firstName = "publicist";
        lastName = "publicist";
        password = "publicist";
        birthday = LocalDate.of(random.nextInt(41) + 1970, Month.JANUARY, 1)
                .plusDays(random.nextInt(365))
                .plusYears(random.nextInt(41));
        Set<Role> publicistRoles = new HashSet<>();
        publicistRoles.add(roleService.findByName(RoleNameEnum.USER));
        publicistRoles.add(roleService.findByName(RoleNameEnum.PUBLICIST));
        User newPublicist = new User();
        newPublicist.setEmail(email);
        newPublicist.setName(firstName);
        newPublicist.setLastName(lastName);
        newPublicist.setPassword(passwordEncoder.encode(password));
        newPublicist.setBirthday(birthday);
        newPublicist.setRoles(publicistRoles);
        userService.update(newPublicist);
        initFolders(newPublicist.getEmail());
    }

    private void initFolders(String email) {
        User user = userService.findByEmail(email);
        for (FolderMovieType folderType : folderTypes) {
            if (folderType != FolderMovieType.CUSTOM) {
                String name = folderType.getName();
                String description = "описание описание описание описание описание описание описание описание ";
                Privacy privacy = Privacy.PUBLIC;
                FolderMovie folder = new FolderMovie();
                folder.setName(name);
                folder.setDescription(description);
                folder.setPrivacy(privacy);
                folder.setUser(user);
                folderService.save(folder);
            }
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    @Order(3)
    public void InitPerson() {
        Random random = new Random();
        int personNum = 1;
        for (int i = 0; i < 50; i++) {
            String firstName = "Персона" + personNum;
            String lastName = "Фамилия" + personNum;
            String originalName = "Name" + personNum;
            String originalLastName = "LastName" + personNum;
            double height = 1.70 + random.nextDouble() * (2.20 - 1.70);
            BigDecimal bd = new BigDecimal(height);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            double roundedHeight = bd.doubleValue();
            LocalDate dateBirth = LocalDate.of(random.nextInt(20) + 1970, Month.JANUARY, 1)
                    .plusDays(random.nextInt(365))
                    .plusYears(random.nextInt(20));
            Person newPerson = new Person();
            newPerson.setFirstName(firstName);
            newPerson.setLastName(lastName);
            newPerson.setOriginalFirstName(originalName);
            newPerson.setOriginalLastName(originalLastName);
            newPerson.setHeight(roundedHeight);
            newPerson.setDateBirth(dateBirth);
            personService.save(newPerson);
            personNum++;
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    @Order(4)
    public void InitProfession() {
        List<String> prof = Arrays.asList(
                "Актер",
                "Режиссер",
                "Сценарист",
                "Продюсер",
                "Оператор",
                "Композитор",
                "Художник",
                "Монтаж",
                "Звукорежиссер",
                "Каскадер"
        );
        for (String profession : prof) {
            Profession newProfession = new Profession();
            newProfession.setName(profession);
            professionService.save(newProfession);
        }
    }

    @Order(5)
    @EventListener(ApplicationReadyEvent.class)
    public void initGenres() {
        System.out.println("Starting Genre initialization");
        for (int i = 1; i <= 10; i++) {
            var genre = new Genre();
            genre.setName("Жанр " + i);
            genreService.save(genre);
        }
        System.out.println("Genre initialization completed");
    }

    @Order(6)
    @EventListener(ApplicationReadyEvent.class)
    public void initMovies() {
        System.out.println("Starting Movie initialization");
        var random = new Random();
        List<Long> genres = new ArrayList<>(genreService.findAll().stream()
                .map(Genre::getId).toList());
        for (int i = 1; i <= 100; i++) {
            Collections.shuffle(genres, random);
            var movie = new MovieRequestDto();
            movie.setName("Фильм " + i);
            movie.setOriginalName("Original Movie " + i);
            movie.setDateRelease(LocalDate.ofEpochDay(random.nextLong(
                    LocalDate.of(1990, 1, 1).toEpochDay(),
                    LocalDate.now().plusDays(1).toEpochDay())));
            movie.setRars(RARS.randomRARS());
            movie.setMpaa(MPAA.randomMPAA());
            movie.setTime(random.nextInt(100, 181));
            movie.setDescription("Описание описание описание описание описание " + i);
            movie.setGenreIds(genres.stream().limit(3).toList());
            movieService.save(movie);
        }
        System.out.println("Movie initialization completed");
    }

    @Order(7)
    @EventListener(ApplicationReadyEvent.class)
    public void initCollections() {
        System.out.println("Starting Collection initialization");
        for (int i = 1; i <= 20; i++) {
            var collection = new Collection();
            collection.setName("Коллекция " + i);
            collection.setEnable(i <= 15);
            collectionService.save(collection);
        }
        System.out.println("Collection initialization completed");
    }

    @Order(8)
    @EventListener(ApplicationReadyEvent.class)
    public void initCollectionMovies() throws CollectionNotFoundException, MovieNotFoundException {
        System.out.println("Starting CollectionMovies initialization");
        List<Long> movies = new ArrayList<>(movieService.findAll().stream()
                .map(Movie::getId).toList());
        Random random = new Random();
        List<Collection> collections = collectionService.findAll();
        for (Collection collection : collections) {
            Collections.shuffle(movies, random);
            collectionService.addMovieToCollection(collection, movies.subList(0, random.nextInt(11) + 5));
        }
        System.out.println("CollectionMovies initialization completed");
    }

    @Order(9)
    @EventListener(ApplicationReadyEvent.class)
    public void initScores() {
        System.out.println("Starting Score initialization");
        Random random = new Random();
        List<Movie> movies = movieService.findAll();
        List<User> users = userService.findAll();
        for (Movie movie : movies) {
            Collections.shuffle(users);
            for (int i = 0; i < 20; i++) {
                Score score = new Score();
                score.setMovie(movie);
                score.setScore(random.nextInt(10) + 1);
                User user = users.get(i);
                System.out.println("Creating score for movie " + movie.getId() + " and user " + user.getId());
                scoreService.createScore(movie.getId(), score.getScore(), user.getId());
            }
        }
        System.out.println("Score initialization completed");
    }

    private LocalDateTime generateRandomDate() {
        LocalDateTime now = LocalDateTime.now();
        int daysToSubtract = new Random().nextInt(7);
        return now.minusDays(daysToSubtract);
    }

    @Order(10)
    @EventListener(ApplicationReadyEvent.class)
    public void initMedia() {
        System.out.println("Starting Media initialization");
        for (int i = 1; i <= 20; i++) {
            Media media = new Media();
            media.setDate(generateRandomDate());
            media.setTitle("Заголовок " + i);
            media.setHtmlBody("<html>Текст</html>");
            mediaService.createMedia(media);
        }
        System.out.println("Media initialization completed");
    }

    private LocalDate generateRandomDateForReview() {
        LocalDate now = LocalDate.now();
        int daysToSubtract = new Random().nextInt(30);
        LocalDate result = now.minusDays(daysToSubtract);
        System.out.println("Generated date: " + result);
        return result;
    }

    @Order(11)
    @EventListener(ApplicationReadyEvent.class)
    public void initReviews() {
        System.out.println("Starting Review initialization");
        List<Movie> movies = movieService.findAll();
        List<User> users = userService.findAll();
        TypeReview[] types = {TypeReview.POSITIVE, TypeReview.POSITIVE, TypeReview.NEGATIVE, TypeReview.NEGATIVE, TypeReview.NEUTRAL};
        for (Movie movie : movies) {
            for (int i = 0; i < 5; i++) {
                Review review = new Review();
                review.setMovie(movie);
                review.setTitle("Заголовок " + (i + 1));
                review.setTypeReview(types[i]);
                review.setDescription("описание...описание...описание...");
                review.setDate(generateRandomDateForReview());
                User user = users.get(i);
                review.setUser(user);
                ReviewRequestDto reviewRequestDto = new ReviewRequestDto(review.getTypeReview(), review.getTitle(), review.getDescription(), review.getDate());
                reviewService.save(movie.getId(), user.getId(), reviewRequestDto);
            }
        }
        System.out.println("Review initialization completed");
    }
}