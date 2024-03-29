package View;

/**
 * Created with IntelliJ IDEA.
 * User: TheRusskiy
 * Date: 30.12.12
 * Time: 1:51
 * To change this template use File | Settings | File Templates.
 */
public enum Messages {
    MainTitle("Tunnel simulator","Симулятор тоннеля"),

    File("File", "Файл"),
    Cars("Cars","Машины"),
    ShowCars("Show car models","Показать модели"),
    CarModelLoad("Load models", "Загрузить модели"),
    CarModelSave("Save models", "Сохранить модели"),
    CarFlow("Car Flow","Поток машин"),
    NormalFlow("Normal","Нормальный"),
    ExponentialFlow("Exponential","Показательный"),
    UniformFlow("Uniform","Равномерный"),
    DeterminedFlow("Determined","Детерминированный"),
    Language("Language","Язык"),
    EnglishLanguage("English","English"),
    RussianLanguage("Русский","Русский"),
    Exit("Exit", "Выход"),
    HelpMenu("Help","Помощь"),
    GuideItem("User guide", "Руководство пользователя"),
    AuthorsItem("Authors", "Об авторах"),
    AboutItem("About", "О программе"),

    CarControlsTitle("Car Controls", "Ручное управление"),
    NextCar("Next Car", "Следующая"),
    PreviousCar("Previous Car", "Предыдущая"),
    SpeedLabel("Speed", "Скорость"),
    SpeedApply("Apply Speed", "Применить"),

    ModelPropertiesTitle("Model properties", "Параметры модели"),
    VMaxLabel("Vmax (m/s)", "Vмакс (м/с)"),
    StepTimeLabel("Step time(sec)", "Время шага(сек)"),
    ModelPropertiesAccDividerLabel("Time to accelerate(sec)", "Время для ускорения(сек)"),
    ModelPropertiesAcceleration("Acceleration", "Ускорение"),
    ModelPropertiesApply("Apply", "Применить"),

    RoadPropertiesTitle("Road Properties", "Параметры дороги"),
    RoadLengthLabel("Road length", "Длина дороги"),
    ZoomLabel("Zoom", "Увеличение"),
    RoadPropertiesApply("Apply", "Применить"),

    TimeControlsTitle("Time Controls", "Контроль времени"),
    AutoDelayLabel("Auto delay(ms)", "Задержка(мс)"),
    AutoDelayApply("Apply", "Применить"),
    NextStepButton("Next Step", "Следующий шаг"),
//    TunnelView.AutoSimulationLabel("Auto simulation", "Автосимуляция"),
//    AutoOn("On", "Вкл"),
//    AutoOff("Off", "Выкл"),
    TimeControlsAutoCheckbox("Autosimulation", "Автосимуляция"),

    TimePast("Time past(sec):", "Время симуляции(сек):"),

    DeterminedTitle("Determined flow controls", "Детерминированный поток"),
    DeterminedT("ΔT(sec)", "ΔT(сек)"),
    DeterminedApply("Apply", "Применить"),

    ExponentialTitle("Exponential flow controls", "Показательный поток"),
    ExponentialT("Scale(%)", "Масштаб(%)"),
    ExponentialLambda("Rate", "Интенсивность"),
    ExponentialApply("Apply", "Применить"),

    NormalTitle("Normal flow controls", "Нормальный поток"),
    NormalMean("Mean(%)", "Мат. ожидание(%)"),
    NormalDeviation("Variance((%)", "Дисперсия(%)"),
    NormalApply("Apply", "Применить"),

    UniformTitle("Uniform flow controls", "Равномерный поток"),
    UniformT("T(sec)", "Т(сек)"),
    UniformApply("Apply", "Применить"),

    CarModelsTitle("Car models", "Модели машин"),
    CarModelName("Name", "Название"),
    //CarModelNameFiller("", ""),
    CarModelSpeed("Max Speed(m/s)", "Макс. скорость(м/с)"),
    CarPictureLabel("Picture", "Картинка"),
    //CarModelSPeedFiller("", ""),
    CarModelAdd("Add model", "Добавить модель"),
    CarModelDelete("Delete selected", "Удалить выбранную"),

    SaveCarsDialog("Save car models", "Сохранить модели в файл"),
    SaveCarsDialogAccept("Save", "Сохранить"),
    LoadCarsDialog("Load car models", "Загрузить модели из файла"),
    LoadCarsDialogAccept("Load", "Загрузить"),
    CarsFileDescription("Car models file(.cars)", "Файл с моделями машин(.cars)"),
    CarsLoadExceptionTitle("Loading error", "Ошибка загрузки"),
    CarsLoadExceptionMessage("Can't load models from selected file!", "Невозможно прочесть выбранный файл"),
    CarsSaveExceptionTitle("Saving error", "Ошибка сохранения"),
    CarsSaveExceptionMessage("Can't save models to file!", "Невозможно сохранить в файл"),
    CarsDeleteNoneSelectedDialogTitle("No selected models", "Нет выбранных моделей"),
    CarsDeleteNoneSelectedDialogMessage("No selected rows, you have to click on a row you want to delete!", "Нет выбранных моделей, необходимо выделить мышью модель, которую вы хотите удалить!"),

    UserGuideTitle("User guide", "Руководство пользователя"),
    UserGuideText(
            //ENG:
            ""
            ,//RUS:
            ""
    ),
    UserGuidePath("guide_en.html", "guide_ru.html"),
    UserGuideExceptionTitle("Missing file", "Отсутствие файла"),
    UserGuideExceptionMessage("Guide file can't be found!", "Отсутствует файл справки!"),
    AuthorsTitle("Authors", "Об авторах"),
    AuthorsText(
            //ENG:
            "MINISTRY OF EDUCATION AND SCIENCE OF THE RUSSIAN FEDERATION\n" +
            "FEDERAL STATE EDUCATIONAL INSTITUTION\n" +
            "OF HIGHER PROFESSIONAL EDUCATION\n" +
            "«SAMARA STATE AEROSPACE UNIVERSITY\n" +
            "OF ACADEMICIAN S.P. KOROLYOV»\n" +
            "(NATIONAL RESEARCH UNIVERSITY) (SSAU) \n" +
            "Chair of Computer Systems\n" +
            "\n" +
            "Author: Dmitry Ishkov\n" +
            "Group: 6402\n" +
            "Instructor: associate professor Larisa Zelenko"
            ,//RUS:
            "МИНИСТЕРСТВО ОБРАЗОВАНИЯ И НАУКИ РОССИЙСКОЙ ФЕДЕРАЦИИ\n" +
            "ФЕДЕРАЛЬНОЕ ГОСУДАРСТВЕННОЕ БЮДЖЕТНОЕ ОБРАЗОВАТЕЛЬНОЕ УЧРЕЖДЕНИЕ\n" +
            "ВЫСШЕГО ПРОФЕССИОНАЛЬНОГО ОБРАЗОВАНИЯ\n" +
            "«САМАРСКИЙ ГОСУДАРСТВЕННЫЙ АЭРОКОСМИЧЕСКИЙ УНИВЕРСИТЕТ\n" +
            "ИМЕНИ АКАДЕМИКА С. П. КОРОЛЕВА»\n" +
            "(НАЦИОНАЛЬНЫЙ ИССЛЕДОВАТЕЛЬСКИЙ УНИВЕРСИТЕТ) (СГАУ) \n" +
            "Кафедра программных систем\n" +
            "\n" +
            "Автор: Ишков Д.С.\n" +
            "Группа: 6402\n" +
            "Руководитель: доцент кафедры ПС Зеленко Л.С."
    ),
    AboutTitle("About", "О программе"),
    AboutText(
            //ENG:
            ""
            ,//RUS:
            ""
    ),
    ;



    public enum Languages{
        English, Russian;
    }
    private static Languages selected_language=Languages.English;
    private final String english_message;
    private final String russian_message;
    Messages(String english_message, String russian_message){
        this.english_message=english_message;
        this.russian_message=russian_message;
    }
    public static void setLanguage(Languages language){
        selected_language=language;
    }
    public String getMessage(){
        switch (selected_language){
            case English:{
                return english_message;
            }
            case Russian:{
                return russian_message;
            }
            default: {
                throw new RuntimeException("Unknown language!");
            }
        }
    }
}
