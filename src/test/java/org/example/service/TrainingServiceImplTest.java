//package org.example.service;
//
//import org.example.dao.TrainingDAO;
//import org.example.in.dto.AddTrainingRequest;
//import org.example.model.Extra;
//import org.example.model.Training;
//import org.example.model.Type;
//import org.example.model.enumerates.Role;
//import org.example.service.impl.TrainingServiceImpl;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertIterableEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("training service implementation test")
//class TrainingServiceImplTest {
//
//    @InjectMocks
//    private TrainingServiceImpl trainingService;
//    @Mock
//    private TrainingDAO trainingDAO;
//    static private Training training1;
//    static private Training training2;
//    static private AddTrainingRequest addTrainingRequest;
//    /**
//     * Init.
//     */
//    @BeforeAll
//    static void init() {
//        Extra extra1 = new Extra(1,"name",11);
//        Extra extra2 = new Extra(1,"name",11);
//        Type type1=new Type(1,"type");
//        Type type2=new Type(1,"type");
//        training1 = new Training(1,1, 1,LocalDate.of(2022,4,10),1,type1.getId(),extra1.id);
//        training2 = new Training(1,1, 1,LocalDate.of(2024,4,10),1,type2.getId(),extra2.id);
//        training2 = Training.builder().time(1).calorie(1).
//                date(LocalDate.of(2024,4,10)).typeId(1).extraId(1).build();
//        addTrainingRequest = new AddTrainingRequest(1,1,LocalDate.of(2024,10,4),1,1);
//
//    }
//
//    /**
//     * Test get user history.
//     */
//    @Test
//    @DisplayName("get training method verification test")
//    void testGetTraining() {
//        List<Training> trainings = List.of(
//                training1, training2
//        );
//
//        when(trainingDAO.findAllByUserId(1)).thenReturn(trainings);
//        List<Training> trainingHistory = trainingService.getTraining(1, Role.USER);
//        assertIterableEquals(trainings, trainingHistory);
//    }
//
//    /**
//     * Test add training success.
//     */
//    @Test
//    @DisplayName("add training readings method verification test")
//    void testAddTraining_Success() {
//        LocalDate date = LocalDate.of(2024,4,10);
//        Type type = new Type(1,"type");
//
//        trainingService.addTraining(addTrainingRequest, training2.userId);
//
//        Mockito.lenient().when(trainingDAO.findByDate(date, type.getId())).thenReturn(null);
//
//        Mockito.verify(trainingDAO, Mockito.times(1)).save(any());
//    }
//}