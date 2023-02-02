package weather;

import java.util.HashMap;
import java.util.Iterator;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import weather.ipma_client.CityForecast;
import weather.ipma_client.IpmaCityForecast;
import weather.ipma_client.IpmaService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    private static HashMap<String, Integer> city_ids = new HashMap<String, Integer>();
    /*
    loggers provide a better alternative to System.out.println
    https://rules.sonarsource.com/java/tag/bad-practice/RSPEC-106
     */
    private static Logger logger = LogManager.getLogger(WeatherStarter.class);

    public static void  main(String[] args ) {

        city_ids.put("aveiro", 1010500);
        city_ids.put("beja", 1020500);
        city_ids.put("braga", 1030300);
        city_ids.put("bragança", 1040200);
        city_ids.put("castelo branco", 1050200);
        city_ids.put("coimbra", 1060300);
        city_ids.put("évora", 1070500);
        city_ids.put("faro", 1080500);
        city_ids.put("guarda", 1090700);
        city_ids.put("leiria", 1100900);
        city_ids.put("lisboa", 1110600);
        city_ids.put("portalegre", 1121400);
        city_ids.put("porto", 1131200);
        city_ids.put("santarém", 1141600);
        city_ids.put("setúbal", 1151200);
        city_ids.put("viana do castelo", 1160900);
        city_ids.put("vila real", 1171400);
        city_ids.put("viseu", 1182300);
        city_ids.put("funchal", 2310300);
        city_ids.put("porto santo", 2320100);
        city_ids.put("vila do porto", 3410100);
        city_ids.put("ponta delgada", 3420300);

        final int id = city_ids.get(args[0].toLowerCase());
        /*
        get a retrofit instance, loaded with the GSon lib to convert JSON into objects
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getForecastForACity(id);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                Iterator<CityForecast> iterator = forecast.getData().listIterator();
                String info = args[0].toUpperCase() + "\n";

                while(iterator.hasNext()){
                    CityForecast forecastInfo = iterator.next();

                    info +="\n" + forecastInfo.getForecastDate() + "\n" + 
                                "\tMax Temp: " + forecastInfo.getTMax() + "ºC\n" + 
                                "\tMin Temp: " + forecastInfo.getTMin() + "ºC\n" + 
                                "\tPrecipitation Probability: " + forecastInfo.getPrecipitaProb() + "%\n" + 
                                "\tLongitude: " + forecastInfo.getLongitude() + "º\n" + 
                                "\tLatitude: " + forecastInfo.getLatitude() + "º\n";  
                    
                }
                logger.info(info);
            } else {
                logger.info( "No results!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
