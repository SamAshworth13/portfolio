package solution;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import baseclasses.*;
import baseclasses.Pilot.Rank;

/**
 * The Scheduler class is responsible for deciding which aircraft and crew will be
 * used for each of an airline's flights in a specified period of time, referred to
 * as a "scheduling horizon". A schedule must have an aircraft, two pilots, and 
 * sufficient cabin crew for the aircraft allocated to every flight in the horizon 
 * to be valid.
 */
public class Scheduler implements IScheduler 
{

	/**
	 * Generates a schedule, providing you with ready-loaded DAO objects to get your data from
	 * @param aircraftDAO the DAO for the aircraft to be used when scheduling
	 * @param crewDAO the DAO for the crew to be used when scheduling
	 * @param routeDAO the DAO to use for routes when scheduling
	 * @param passengerNumbersDAO the DAO to use for passenger numbers when scheduling
	 * @param startDate the start of the scheduling horizon
	 * @param endDate the end of the scheduling horizon
	 * @return The generated schedule - which must happen inside 2 minutes
	 */
	@Override
	public Schedule generateSchedule(IAircraftDAO aircraftDAO, ICrewDAO crewDAO, IRouteDAO routeDAO, 
			IPassengerNumbersDAO passengerNumbersDAO, LocalDate startDate, LocalDate endDate) 
	{
		//create an empty schedule then fill it with data
		Schedule finalSchedule = null;
		finalSchedule = new Schedule(routeDAO, startDate, endDate);
		
		//create lists of all pilots and aircraft
		List<Aircraft> allAircraft = aircraftDAO.getAllAircraft();
		List<Pilot> allPilots = crewDAO.getAllPilots();
		List<Pilot> allCaptains = new ArrayList<>();
		List<Pilot> allFOs = new ArrayList<>();
		
		//split list of pilots in list of captains and list of first officers
		for (Pilot p : allPilots)
		{
			if (p.getRank().equals(Rank.CAPTAIN))
			{
				allCaptains.add(p);
			}
			else
			{
				allFOs.add(p);
			}
		}
		
		//sort the schedule in order of date
		finalSchedule.sort();
		
		//get list of all incomplete allocations
		List<FlightInfo> remList = finalSchedule.getRemainingAllocations();
		//loop through remList, providing allocations for each
		System.out.println(remList.size());
		while (remList.size() > 0)
		{
			List <FlightInfo> pairOfFlights = new ArrayList<>();
			if (remList.size() > 1)
			{
				//link all flights into pairs where possible and remove them from remList
				pairOfFlights = matchFlights(remList);
				if(pairOfFlights.size() > 1)
				{
					remList.remove(pairOfFlights.get(0));
					remList.remove(pairOfFlights.get(1));
				}
				else
				{
					pairOfFlights = matchRemainingFlights(remList);
					if (pairOfFlights.size() > 1)
					{
						remList.remove(pairOfFlights.get(0));
						remList.remove(pairOfFlights.get(1));
					}
				}
			}
			
			if (pairOfFlights.size() > 1)
			{
				FlightInfo flight1 = pairOfFlights.get(0);
				FlightInfo flight2 = pairOfFlights.get(1);
				
				//get dates and flight numbers of flights to find expected number of passengers
				LocalDate flight1Date = flight1.getDepartureDateTime().toLocalDate();
				LocalDate flight2Date = flight2.getDepartureDateTime().toLocalDate();
				
				int flight1Number = flight1.getFlight().getFlightNumber();
				int flight2Number = flight2.getFlight().getFlightNumber();
				
				int flight1Passengers = passengerNumbersDAO.getPassengerNumbersFor(flight1Number, flight1Date);
				int flight2Passengers = passengerNumbersDAO.getPassengerNumbersFor(flight2Number, flight2Date);
				
				int minSeats = 0;
				
				//find the flight expecting the most passengers and store its expected passengers
				if (flight1Passengers > flight2Passengers)
				{
					minSeats = flight1Passengers;
				}
				else
				{
					minSeats = flight2Passengers;
				}
			
				int bestSeats = 100000;
				Aircraft chosenAC = null;
				
				//loop through all aircraft finding available ones with enough seats
				for (Aircraft a : allAircraft)
				{
					if (!finalSchedule.hasConflict(a, flight1))
					{
						if (!finalSchedule.hasConflict(a, flight2))
						{
							if (a.getSeats() >= minSeats)
							{
								if (a.getSeats() < bestSeats)
								{
									bestSeats = a.getSeats();
									chosenAC = a;
								}
							}
						}
					}
				}
			
				//allocate the same aircraft to both flights
				if (chosenAC != null)
				{
					//System.out.println(chosenAC.getTailCode());
					try
					{
						finalSchedule.allocateAircraftTo(chosenAC, flight1);
						finalSchedule.allocateAircraftTo(chosenAC, flight2);
					}
					catch (DoubleBookedException dbe)
					{
						//dbe.printStackTrace();
					}
					
					
					//find a suitable captain and first officer
					Pilot captain = chooseCaptain(allCaptains, flight1, flight2, finalSchedule);
					Pilot firstOfficer = chooseFO(allFOs, allCaptains, flight1, flight2, finalSchedule);
					
					//allocate captain and first officer
					if (captain != null)
					{
						try
						{
							finalSchedule.allocateCaptainTo(captain, flight1);
							finalSchedule.allocateCaptainTo(captain, flight2);
						}
						catch (DoubleBookedException dbe)
						{
							//dbe.printStackTrace();
						}
					}
					else
					{
						finalSchedule.unAllocate(flight1);
						finalSchedule.unAllocate(flight2);
					}
					
					if (firstOfficer != null)
					{
						try
						{
							finalSchedule.allocateFirstOfficerTo(firstOfficer, flight1);
							finalSchedule.allocateFirstOfficerTo(firstOfficer, flight2);
						}
						catch (DoubleBookedException dbe)
						{
							//dbe.printStackTrace();
						}
					}
					else
					{
						finalSchedule.unAllocate(flight1);
						finalSchedule.unAllocate(flight2);
					}
			
					//find out the number of cabin crew required for the chosen aircraft
					int crewReq = finalSchedule.getAircraftFor(flight1).getCabinCrewRequired();
					
					//create a list of all cabin crew
					List<CabinCrew> allCabCrew = crewDAO.getAllCabinCrew();
			
					//loop the same amount of times as the number of cabin crew required
					for (int i = 0; i < crewReq; i++)
					{
						//find a suitable cabin crew member
						CabinCrew crewmate = chooseCabCrew(allCabCrew, flight1, flight2, finalSchedule);
						allCabCrew.remove(crewmate);
						
						//allocate the cabin crew member
						if (crewmate != null)
						{
							try
							{
								finalSchedule.allocateCabinCrewTo(crewmate, flight1);
								finalSchedule.allocateCabinCrewTo(crewmate, flight2);
							}
							catch (DoubleBookedException dbe)
							{
								//dbe.printStackTrace();
							}
						}
						else
						{
							finalSchedule.unAllocate(flight1);
							finalSchedule.unAllocate(flight2);
						}
					}
					
					//complete the allocations for the current pair of flights, marking them as done
					try
					{
						finalSchedule.completeAllocationFor(flight1);
						finalSchedule.completeAllocationFor(flight2);
					}
					catch (InvalidAllocationException iae)
					{
						//iae.printStackTrace();
					}
				}
			}
			
			
			//if there are unmatched flights, try to allocate them
			remList = finalSchedule.getRemainingAllocations();
			for (FlightInfo flight : remList)
			{
				//get date and flight number to find expected number of passengers
				LocalDate flight1Date = flight.getDepartureDateTime().toLocalDate();
				
				int flightNumber = flight.getFlight().getFlightNumber();
				
				int flightPassengers = passengerNumbersDAO.getPassengerNumbersFor(flightNumber, flight1Date);
				
				int bestSeats = 100000;
				Aircraft chosenAC = null;
				
				//loop through all aircraft finding available ones with enough seats
				for (Aircraft a : allAircraft)
				{
					if (!finalSchedule.hasConflict(a, flight))
					{
						if (!finalSchedule.hasConflict(a, flight))
						{
							if (a.getSeats() >= flightPassengers)
							{
								if (a.getSeats() < bestSeats)
								{
									bestSeats = a.getSeats();
									chosenAC = a;
								}
							}
						}
					}
				}
				//if no aircraft was chosen, allocate the first one without a conflict
				if (chosenAC == null)
				{
					for (Aircraft a : allAircraft)
					{
						if (!finalSchedule.hasConflict(a, flight))
						{
							chosenAC = a;
							break;
						}
					}
				}
				
				//allocate the aircraft to the flight
				if (chosenAC != null)
				{
					//System.out.println(chosenAC.getTailCode());
					try
					{
						finalSchedule.allocateAircraftTo(chosenAC, flight);
					}
					catch (DoubleBookedException dbe)
					{
						//dbe.printStackTrace();
					}
					
					
					//find a suitable captain and first officer
					Pilot captain = chooseCaptain(allCaptains, flight, finalSchedule);
					Pilot firstOfficer = chooseFO(allFOs, allCaptains, flight, finalSchedule);
					
					//allocate captain and first officer
					if (captain != null)
					{
						try
						{
							finalSchedule.allocateCaptainTo(captain, flight);
						}
						catch (DoubleBookedException dbe)
						{
							//dbe.printStackTrace();
						}
					}
					else
					{
						finalSchedule.unAllocate(flight);
					}
					
					if (firstOfficer != null)
					{
						try
						{
							finalSchedule.allocateFirstOfficerTo(firstOfficer, flight);
						}
						catch (DoubleBookedException dbe)
						{
							//dbe.printStackTrace();
						}
					}
					else
					{
						finalSchedule.unAllocate(flight);
					}
					
					//find out the number of cabin crew required for the chosen aircraft
					int crewReq = finalSchedule.getAircraftFor(flight).getCabinCrewRequired();
					
					//create a list of all cabin crew
					List<CabinCrew> allCabCrew = crewDAO.getAllCabinCrew();
					
					//loop the same amount of times as the number of cabin crew required
					for (int i = 0; i < crewReq; i++)
					{
						//find a suitable cabin crew member
						CabinCrew crewmate = chooseCabCrew(allCabCrew, flight, finalSchedule);
						allCabCrew.remove(crewmate);
						
						//allocate the cabin crew member
						if (crewmate != null)
						{
							try
							{
								finalSchedule.allocateCabinCrewTo(crewmate, flight);
							}
							catch (DoubleBookedException dbe)
							{
								//dbe.printStackTrace();
							}
						}
						else
						{
							finalSchedule.unAllocate(flight);
						}
					}
			
					//complete the allocations for the current pair of flights, marking them as done
					try
					{
						finalSchedule.completeAllocationFor(flight);
					}
					catch (InvalidAllocationException iae)
					{
						//iae.printStackTrace();
					}
				}
			}
		}
		
		
		//Loop through remaining flights one last time with as few requirements as possible
		remList = finalSchedule.getRemainingAllocations();
		for (FlightInfo fi : remList)
		{
			Aircraft chosenAC = null;
			
			for (Aircraft a : allAircraft)
			{
				if (!finalSchedule.hasConflict(a, fi))
				{
					chosenAC = a;
					break;
				}
			}
			
			try
			{
				finalSchedule.allocateAircraftTo(chosenAC, fi);
			}
			catch (DoubleBookedException dbe)
			{
				//dbe.printStackTrace();
			}
			
			Pilot chosenFO = null;
			
			for (Pilot p : allPilots)
			{
				if (!finalSchedule.hasConflict(p, fi))
				{
					chosenFO = p;
				}
			}
			
			try
			{
				finalSchedule.allocateFirstOfficerTo(chosenFO, fi);
			}
			catch (DoubleBookedException dbe)
			{
				//dbe.printStackTrace();
			}
			
			Pilot chosenCap = null;
			
			for (Pilot p : allPilots)
			{
				if (!finalSchedule.hasConflict(p, fi))
				{
					chosenCap = p;
				}
			}
			
			try
			{
				finalSchedule.allocateCaptainTo(chosenCap, fi);
			}
			catch (DoubleBookedException dbe)
			{
				//dbe.printStackTrace();
			}
			
			List<CabinCrew> allCabCrew = crewDAO.getAllCabinCrew();
			CabinCrew chosenCabCrew = null;
			
			for (CabinCrew cc : allCabCrew)
			{
				if (!finalSchedule.hasConflict(cc, fi))
				{
					chosenCabCrew = cc;
				}
			}
			
			try
			{
				finalSchedule.allocateCabinCrewTo(chosenCabCrew, fi);
			}
			catch (DoubleBookedException dbe)
			{
				//dbe.printStackTrace();
			}
			
			try
			{
				finalSchedule.completeAllocationFor(fi);
			}
			catch (InvalidAllocationException iae)
			{
				//iae.printStackTrace();
			}
			
		}
		
		return finalSchedule;
	}
	
	/*
	private List<FlightInfo> linkFlights(List<FlightInfo> fullList)
	{
		boolean success = false;
		List<FlightInfo> linkedFlights = new ArrayList<>();
		
		//get the first flight in the list
		FlightInfo flight1 = fullList.get(0);
		
		//loop through the rest of the list to find a return flight for the first one
		for (int i = 1 ; i < fullList.size(); i++)
		{
			FlightInfo flight2 = fullList.get(i);
			if (flight1.getLandingDateTime().toLocalDate().equals(flight2.getDepartureDateTime().toLocalDate()))
			{
				if (flight1.getLandingDateTime().isBefore(flight2.getDepartureDateTime()))
				{
					if (flight1.getFlight().getDepartureAirportCode().equals(flight2.getFlight().getArrivalAirportCode()))
					{
						if(flight1.getFlight().getArrivalAirportCode().equals(flight2.getFlight().getDepartureAirportCode()))
						{
							//add both flights to the list
							linkedFlights.add(flight1);
							linkedFlights.add(flight2);
							success = true;
							break;
						}
					}
				}
			}
		}
		
		//if no matching flight was found, start with the second flight on the list instead
		if (!success)
		{
			flight1 = fullList.get(1);
			for (int i = 0 ; i < fullList.size(); i++)
			{
				if (i != 1)
				{
					FlightInfo flight2 = fullList.get(i);
					if (flight1.getLandingDateTime().toLocalDate().equals(flight2.getDepartureDateTime().toLocalDate()))
					{
						if (flight1.getLandingDateTime().isBefore(flight2.getDepartureDateTime()))
						{
							if (flight1.getFlight().getDepartureAirportCode().equals(flight2.getFlight().getArrivalAirportCode()))
							{
								if(flight1.getFlight().getArrivalAirportCode().equals(flight2.getFlight().getDepartureAirportCode()))
								{
									linkedFlights.add(flight1);
									linkedFlights.add(flight2);
									success = true;
									break;
								}
							}
						}
					}
				}

			}	
		}
	//return the pair of flights
	return linkedFlights;
	}
	
	private List<FlightInfo> linkRemainingFlights(List<FlightInfo> fullList)
	{
		//works the same as linkFlights but doesn't force the flights to be on the same day
		boolean success = false;
		List<FlightInfo> linkedFlights = new ArrayList<>();
		FlightInfo flight1 = fullList.get(0);
		for (int i = 1 ; i < fullList.size(); i++)
		{
			FlightInfo flight2 = fullList.get(i);
			
			if (flight1.getLandingDateTime().isBefore(flight2.getDepartureDateTime()))
			{
				if (flight1.getFlight().getDepartureAirportCode().equals(flight2.getFlight().getArrivalAirportCode()))
				{
					if(flight1.getFlight().getArrivalAirportCode().equals(flight2.getFlight().getDepartureAirportCode()))
					{
						linkedFlights.add(flight1);
						linkedFlights.add(flight2);
						success = true;
						break;
					}
				}
			}
		}
		if (!success)
		{
			flight1 = fullList.get(1);
			for (int i = 0 ; i < fullList.size(); i++)
			{
				if (i != 1)
				{
					FlightInfo flight2 = fullList.get(i);
					
					if (flight1.getLandingDateTime().isBefore(flight2.getDepartureDateTime()))
					{
						if (flight1.getFlight().getDepartureAirportCode().equals(flight2.getFlight().getArrivalAirportCode()))
						{
							if(flight1.getFlight().getArrivalAirportCode().equals(flight2.getFlight().getDepartureAirportCode()))
							{
								linkedFlights.add(flight1);
								linkedFlights.add(flight2);
								success = true;
								break;
							}
						}
					}
				}

			}	
		}
	return linkedFlights;
	}
	
	*/
	
	private List<FlightInfo> matchFlights(List<FlightInfo> fullList)
	{
		//works the same as linkFlights but doesn't force the flights to land back at the airport they started at
		boolean success = false;
		List<FlightInfo> linkedFlights = new ArrayList<>();
		
		//get the first flight in the list
		FlightInfo flight1 = fullList.get(0);
		
		//loop through the rest of the list to find a return flight for the first one
		for (int i = 1 ; i < fullList.size(); i++)
		{
			FlightInfo flight2 = fullList.get(i);
			if (flight1.getLandingDateTime().toLocalDate().equals(flight2.getDepartureDateTime().toLocalDate()))
			{
				if (flight1.getLandingDateTime().isBefore(flight2.getDepartureDateTime()))
				{
					if(flight1.getFlight().getArrivalAirportCode().equals(flight2.getFlight().getDepartureAirportCode()))
					{
						//add both flights to the list
						linkedFlights.add(flight1);
						linkedFlights.add(flight2);
						success = true;
						break;
					}
				}
			}
		}
		
		//if no matching flight was found, start with the second flight on the list instead
		if (!success)
		{
			flight1 = fullList.get(1);
			for (int i = 0 ; i < fullList.size(); i++)
			{
				if (i != 1)
				{
					FlightInfo flight2 = fullList.get(i);
					if (flight1.getLandingDateTime().toLocalDate().equals(flight2.getDepartureDateTime().toLocalDate()))
					{
						if (flight1.getLandingDateTime().isBefore(flight2.getDepartureDateTime()))
						{
							if(flight1.getFlight().getArrivalAirportCode().equals(flight2.getFlight().getDepartureAirportCode()))
							{
								linkedFlights.add(flight1);
								linkedFlights.add(flight2);
								success = true;
								break;
							}
						}
					}
				}

			}	
		}
	//return the pair of flights
	return linkedFlights;
	}
	
	private List<FlightInfo> matchRemainingFlights(List<FlightInfo> fullList)
	{
		//works the same as linkFlights but doesn't force the flights to be on the same day or land back at the airport they started at
		boolean success = false;
		List<FlightInfo> linkedFlights = new ArrayList<>();
		FlightInfo flight1 = fullList.get(0);
		for (int i = 1 ; i < fullList.size(); i++)
		{
			FlightInfo flight2 = fullList.get(i);
			
			if (flight1.getLandingDateTime().isBefore(flight2.getDepartureDateTime()))
			{
				if(flight1.getFlight().getArrivalAirportCode().equals(flight2.getFlight().getDepartureAirportCode()))
				{
					linkedFlights.add(flight1);
					linkedFlights.add(flight2);
					success = true;
					break;
				}
			}
		}
		if (!success)
		{
			flight1 = fullList.get(1);
			for (int i = 0 ; i < fullList.size(); i++)
			{
				if (i != 1)
				{
					FlightInfo flight2 = fullList.get(i);
					
					if (flight1.getLandingDateTime().isBefore(flight2.getDepartureDateTime()))
					{
						if(flight1.getFlight().getArrivalAirportCode().equals(flight2.getFlight().getDepartureAirportCode()))
						{
							linkedFlights.add(flight1);
							linkedFlights.add(flight2);
							success = true;
							break;
						}
					}
				}

			}	
		}
	return linkedFlights;
	}
	
	private Pilot chooseCaptain(List<Pilot> captains, FlightInfo f1, FlightInfo f2, Schedule sch)
	{
		Pilot chosenCaptain = null;
		
		//loop through list of captains to find a suitable one
		for (Pilot c : captains)
		{
			if (!sch.hasConflict(c, f1))
			{
				if (!sch.hasConflict(c, f2))
				{
					if (c.isQualifiedFor(sch.getAircraftFor(f1)))
					{
						if (c.getHomeBase().equals(f2.getFlight().getArrivalAirportCode()))
						{
							chosenCaptain = c;
							break;
						}
					}
				}
			}
		}
		
		//if no captain was chosen, try again with fewer restrictions
		if (chosenCaptain == null)
		{
			for (Pilot c : captains)
			{
				if (!sch.hasConflict(c, f1))
				{
					if (!sch.hasConflict(c, f2))
					{
						if (c.isQualifiedFor(sch.getAircraftFor(f1)))
						{
							chosenCaptain = c;
							break;
						}
					}
				}
			}
		}
		
		//if no captain was chosen, try again with fewer restrictions
		if (chosenCaptain == null)
		{
			for (Pilot c : captains)
			{
				if (!sch.hasConflict(c, f1))
				{
					if (!sch.hasConflict(c, f2))
					{
						chosenCaptain = c;
						break;
					}
				}
			}
		}
		return chosenCaptain;
	}
	
	private Pilot chooseCaptain(List<Pilot> captains, FlightInfo f1, Schedule sch)
	{
		Pilot chosenCaptain = null;
		
		//loop through list of captains to find a suitable one
		for (Pilot c : captains)
		{
			if (!sch.hasConflict(c, f1))
			{
				if (c.isQualifiedFor(sch.getAircraftFor(f1)))
				{
					if (c.getHomeBase().equals(f1.getFlight().getDepartureAirportCode()))
					{
						chosenCaptain = c;
						break;
					}
				}
			}
		}
		
		//if no captain was chosen, try again with fewer restrictions
		if (chosenCaptain == null)
		{
			for (Pilot c : captains)
			{
				if (!sch.hasConflict(c, f1))
				{
					if (c.isQualifiedFor(sch.getAircraftFor(f1)))
					{
						chosenCaptain = c;
						break;
					}
				}
			}
		}
		
		//if no captain was chosen, try again with fewer restrictions
		if (chosenCaptain == null)
		{
			for (Pilot c : captains)
			{
				if (!sch.hasConflict(c, f1))
				{
					chosenCaptain = c;
					break;
				}
			}
		}
		return chosenCaptain;
	}
	
	private Pilot chooseFO(List<Pilot> firstOfficers, List<Pilot> captains, FlightInfo f1, FlightInfo f2, Schedule sch)
	{
		Pilot chosenFO = null;
		
		//loop through list of first officers to find a suitable one
		for (Pilot fo : firstOfficers)
		{
			if (!sch.hasConflict(fo, f1))
			{
				if (!sch.hasConflict(fo, f2))
				{
					if (fo.isQualifiedFor(sch.getAircraftFor(f1)))
					{
						if (fo.getHomeBase().equals(f2.getFlight().getArrivalAirportCode()))
						{
							chosenFO = fo;
							break;
						}
					}
				}
			}
		}
		
		//if no first officer was chosen, try again with fewer restrictions
		if (chosenFO == null)
		{
			for (Pilot fo : firstOfficers)
			{
				if (!sch.hasConflict(fo, f1))
				{
					if (!sch.hasConflict(fo, f2))
					{
						if (fo.isQualifiedFor(sch.getAircraftFor(f1)))
						{
							chosenFO = fo;
							break;
						}
					}
				}
			}
		}
		
		//if no first officer was chosen try to allocate a captain as FO instead
		if (chosenFO == null)
		{
			for (Pilot c : captains)
			{
				if (!sch.hasConflict(c, f1))
				{
					if (!sch.hasConflict(c, f2))
					{
						if (c.isQualifiedFor(sch.getAircraftFor(f1)))
						{
							if (c.getHomeBase().equals(f2.getFlight().getArrivalAirportCode()))
							{
								chosenFO = c;
								break;
							}
						}
					}
				}
			}
		}
		
		//if no first officer was chosen, try again with fewer restrictions
		if (chosenFO == null)
		{
			for (Pilot c : captains)
			{
				if (!sch.hasConflict(c, f1))
				{
					if (!sch.hasConflict(c, f2))
					{
						if (c.isQualifiedFor(sch.getAircraftFor(f1)))
						{
							chosenFO = c;
							break;
						}
					}
				}
			}
		}
		
		//if no first officer was chosen, try again with fewer restrictions
		if (chosenFO == null)
		{
			for (Pilot fo : firstOfficers)
			{
				if (!sch.hasConflict(fo, f1))
				{
					if (!sch.hasConflict(fo, f2))
					{
						chosenFO = fo;
						break;
					}
				}
			}
		}
		return chosenFO;
	}
	
	private Pilot chooseFO(List<Pilot> firstOfficers, List<Pilot> captains, FlightInfo f1, Schedule sch)
	{
		Pilot chosenFO = null;
		
		//loop through list of first officers to find a suitable one
		for (Pilot fo : firstOfficers)
		{
			if (!sch.hasConflict(fo, f1))
			{
				if (fo.isQualifiedFor(sch.getAircraftFor(f1)))
				{
					if (fo.getHomeBase().equals(f1.getFlight().getDepartureAirportCode()))
					{
						chosenFO = fo;
						break;
					}
				}
			}
		}
		
		//if no first officer was chosen, try again with fewer restrictions
		if (chosenFO == null)
		{
			for (Pilot fo : firstOfficers)
			{
				if (!sch.hasConflict(fo, f1))
				{
					if (fo.isQualifiedFor(sch.getAircraftFor(f1)))
					{
						chosenFO = fo;
						break;
					}
				}
			}
		}
		
		//if no first officer was chosen try to allocate a captain as FO instead
		if (chosenFO == null)
		{
			for (Pilot c : captains)
			{
				if (!sch.hasConflict(c, f1))
				{
					if (c.isQualifiedFor(sch.getAircraftFor(f1)))
					{
						if (c.getHomeBase().equals(f1.getFlight().getDepartureAirportCode()))
						{
							chosenFO = c;
							break;
						}
					}
				}
			}
		}
		
		//if no first officer was chosen, try again with fewer restrictions
		if (chosenFO == null)
		{
			for (Pilot c : captains)
			{
				if (!sch.hasConflict(c, f1))
				{
					if (c.isQualifiedFor(sch.getAircraftFor(f1)))
					{
						chosenFO = c;
						break;
					}
				}
			}
		}
		
		//if no first officer was chosen, try again with fewer restrictions
		if (chosenFO == null)
		{
			for (Pilot fo : firstOfficers)
			{
				if (!sch.hasConflict(fo, f1))
				{
					chosenFO = fo;
					break;
				}
			}
		}
		return chosenFO;
	}
	
	private CabinCrew chooseCabCrew(List<CabinCrew> allCrew, FlightInfo f1, FlightInfo f2, Schedule sch)
	{
		CabinCrew chosenCrew = null;
		
		//loop through list of cabin crew to find a suitable one
		for (CabinCrew cc : allCrew)
		{
			if (!sch.hasConflict(cc, f1))
			{
				if (!sch.hasConflict(cc, f2))
				{
					if (cc.isQualifiedFor(sch.getAircraftFor(f1)))
					{
						if (cc.getHomeBase().equals(f2.getFlight().getArrivalAirportCode()))
						{
							chosenCrew = cc;
							break;
						}
					}
				}
			}
		}
		
		//if no cabin crew member was chosen, try again with fewer restrictions
		if (chosenCrew == null)
		{
			for (CabinCrew cc : allCrew)
			{
				if (!sch.hasConflict(cc, f1))
				{
					if (!sch.hasConflict(cc, f2))
					{
						if (cc.isQualifiedFor(sch.getAircraftFor(f1)))
						{
							chosenCrew = cc;
							break;
						}
					}
				}
			}
		}
		
		//if no cabin crew member was chosen, try again with fewer restrictions
		if (chosenCrew == null)
		{
			for (CabinCrew cc : allCrew)
			{
				if (!sch.hasConflict(cc, f1))
				{
					if (!sch.hasConflict(cc, f2))
					{
						chosenCrew = cc;
						break;
					}
				}
			}
		}
		
		return chosenCrew;
	}
	
	private CabinCrew chooseCabCrew(List<CabinCrew> allCrew, FlightInfo f1, Schedule sch)
	{
		CabinCrew chosenCrew = null;
		
		//loop through list of cabin crew to find a suitable one
		for (CabinCrew cc : allCrew)
		{
			if (!sch.hasConflict(cc, f1))
			{
				if (cc.isQualifiedFor(sch.getAircraftFor(f1)))
				{
					if (cc.getHomeBase().equals(f1.getFlight().getDepartureAirportCode()))
					{
						chosenCrew = cc;
						break;
					}
				}
			}
		}
		
		//if no cabin crew member was chosen, try again with fewer restrictions
		if (chosenCrew == null)
		{
			for (CabinCrew cc : allCrew)
			{
				if (!sch.hasConflict(cc, f1))
				{
					if (cc.isQualifiedFor(sch.getAircraftFor(f1)))
					{
						chosenCrew = cc;
						break;
					}
				}
			}
		}
		
		//if no cabin crew member was chosen, try again with fewer restrictions
		if (chosenCrew == null)
		{
			for (CabinCrew cc : allCrew)
			{
				if (!sch.hasConflict(cc, f1))
				{
					chosenCrew = cc;
					break;
				}
			}
		}
		
		return chosenCrew;
	}

}
