
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    private static final int CITY_ID_AVEIRO = 1010500;
    /*
    loggers provide a better alternative to System.out.println
    https://rules.sonarsource.com/java/tag/bad-practice/RSPEC-106
     */
    private static final Logger logger = Logger.getLogger(WeatherStarter.class.getName());

    public static void  main(String[] args ) {

        /*
        get a retrofit instance, loaded with the GSon lib to convert JSON into objects
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpmaService service = retrofit.create(IpmaService.class);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter City Code\n \t> ");
        int CITY_CODE = sc.nextInt();


        Call<IpmaCityForecast> callSync = service.getForecastForACity(CITY_CODE);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                CityForecast data = forecast.getData().listIterator().next();
                System.out.printf("Forecast for city %s, %s\n", CITY_CODE, forecast.getCountry() );
                System.out.printf("< %s, %s >\n", data.getLatitude(), data.getLongitude() );
                System.out.printf("Date %s\n", data.getForecastDate());
                System.out.printf("Max: %s, Min: %s, Probability of Precipitation: %s\n", data.getTMax(), data.getTMin(), data.getPrecipitaProb());
            } else {
                logger.info( "No results!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}