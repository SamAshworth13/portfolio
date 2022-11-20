package solution;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import baseclasses.*;

/**
 * This class allows you to run the code in your classes yourself, for testing and development
 */
public class Main 
{

	public static void main(String[] args) 
	{			
		Scheduler myScheduler = new Scheduler();
		IAircraftDAO aircraftDAO = new AircraftDAO();
		ICrewDAO crewDAO = new CrewDAO();
		IRouteDAO routeDAO = new RouteDAO();
		IPassengerNumbersDAO passengerNumbersDAO = new PassengerNumbersDAO();
		try
		{
			//load all files
			aircraftDAO.loadAircraftData(Paths.get("./data/aircraft.csv"));
			crewDAO.loadCrewData(Paths.get("./data/crew.json"));
			routeDAO.loadRouteData(Paths.get("./data/routes.xml"));
			passengerNumbersDAO.loadPassengerNumbersData(Paths.get("./data/passengernumbers.db"));
			
			/*
			List<Route> allRoutes = routeDAO.getAllRoutes();
			
			allRoutes.remove(0);
			
			System.out.println("allRoutes list:");
			
			for (Route r : allRoutes)
			{
				System.out.println(r.getFlightNumber());
			}
			
			System.out.println("\nfullList:");
			
			for (Route r : routeDAO.getAllRoutes())
			{
				System.out.println(r.getFlightNumber());
			}
			*/
			
			
			
			Schedule mySchedule = myScheduler.generateSchedule(aircraftDAO, crewDAO, routeDAO, passengerNumbersDAO, LocalDate.parse("2020-07-01"), LocalDate.parse("2020-08-31"));
			
			List<FlightInfo> allFlights = mySchedule.getCompletedAllocations();
			
			System.out.println("\nCompleted flights: ");
			
			for (FlightInfo fi : allFlights)
			{
				System.out.println(fi.getFlight().getFlightNumber() + " " + fi.getDepartureDateTime().toString() + " " + fi.getLandingDateTime().toString());
			}
			
			System.out.println("Number of completed flights: " + allFlights.size());
			
			System.out.println("\nRemaining flights: ");
			
			for (FlightInfo fi : mySchedule.getRemainingAllocations())
			{
				System.out.println(fi.getFlight().getFlightNumber() + " " + fi.getDepartureDateTime().toString() + " " + fi.getLandingDateTime().toString());
			}
			
		}
		catch (DataLoadingException dle) 
		{
			System.err.println("Error loading data");
			dle.printStackTrace();
		}
		catch (NullPointerException npe) 
		{
			System.err.println("Error loading data");
			npe.printStackTrace();
		}
	}

}
