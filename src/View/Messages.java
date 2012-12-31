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
    CarFlow("Car Flow","Поток машин"),
    NormalFlow("Normal","Нормальный"),
    ExponentialFlow("Exponential","Показательный"),
    UniformFlow("Uniform","Равномерный"),
    DeterminedFlow("Determined","Детерминированный"),
    Language("Language","Язык"),
    EnglishLanguage("English","English"),
    RussianLanguage("Русский","Русский"),
    Exit("Exit", "Выход"),

    CarControlsTitle("Car Controls", "Ручное управление"),
    NextCar("Next Car", "Следующая машина"),
    PreviousCar("Previous Car", "Предыдущая машина"),
    SpeedLabel("Speed", "Скорость"),
    SpeedApply("Apply Speed", "Применить"),

    ModelPropertiesTitle("Model properties", "Параметры модели"),
    VMaxLabel("Vmax (m/s)", "Vмакс (м/с)"),
    StepTimeLabel("Step time(sec)", "Время шага(сек)"),
    ModelPropertiesAcceleration("Acceleration", "Ускорение"),
    ModelPropertiesApply("Apply", "Применить"),

    RoadPropertiesTitle("Road Properties", "Параметры дороги"),
    RoadLengthLabel("Road length", "Длина дороги"),
    ZoomLabel("Zoom", "Увеличение"),
    RoadPropertiesApply("Apply", "Применить"),

    TimeControlsTitle("Time Controls", "Контроль времени"),
    AutoDelayLabel("Auto delay(ms)", "Задержка(мс)"),
    AutoDelayApply("Apply auto delay", "Применить"),
    NextStepButton("Next Step", "Следующий шаг"),
    AutoSimulationLabel("Auto simulation", "Автосимуляция"),
    AutoOn("On", "Вкл"),
    AutoOff("Off", "Выкл"),

    TimePast("Time past(sec):", "Время симуляции(сек):"),

    DeterminedTitle("Determined flow controls", "Детерминированный поток"),
    DeterminedT("T", "T"),
    DeterminedApply("Apply", "Применить"),

    ExponentialTitle("Exponential flow controls", "Показательный поток"),
    ExponentialT("T", "Т"),
    ExponentialLambda("Lambda", "Лямбда"),
    ExponentialApply("Apply", "Применить"),

    NormalTitle("Normal flow controls", "Нормальный поток"),
    NormalMean("Mean", "Среднее"),
    NormalDeviation("Deviation", "Отклонение"),
    NormalApply("Apply", "Применить"),

    UniformTitle("Uniform flow controls", "Равномерный поток"),
    UniformT("T", "Т"),
    UniformApply("Apply", "Применить"),

    CarModelName("Name", "Название"),
    //CarModelNameFiller("", ""),
    CarModelSpeed("Max Speed(m/s)", "Макс. скорость(м/с)"),
    //CarModelSPeedFiller("", ""),
    CarModelAdd("Add model", "Добавить модель"),
    CarModelDelete("Delete selected", "Удалить выбранную"),
    CarModelLoad("Load models", "Загрузить модели"),
    CarModelSave("Save models", "Сохранить модели"),

    SaveCarsDialog("Save car models", "Сохранить модели в файл"),
    SaveCarsDialogAccept("Save", "Сохранить"),
    LoadCarsDialog("Load car models", "Загрузить модели из файла"),
    LoadCarsDialogAccept("Load", "Загрузить"),
    CarsFileDescription("Car models file(.cars)", "Файл с моделями машин(.cars)"),
    CarsLoadExceptionTitle("Loading error", "Ошибка загрузки"),
    CarsLoadExceptionMessage("Can't load models from selected file!", "Невозможно прочесть выбранный файл"),
    CarsDeleteNoneSelectedDialogTitle("No selected models", "Нет выбранных моделей"),
    CarsDeleteNoneSelectedDialogMessage("No selected rows, you have to click on a row you want to delete!", "Нет выбранных моделей, необходимо выделить мышью модель, которую вы хотите удалить!"),
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
