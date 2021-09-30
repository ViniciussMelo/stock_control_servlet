package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import model.Movement;

public class MovementService {
	private final String MOVEMENT = "MOVEMENT";
	
	public void insertMovement(HttpServletRequest req) {
		String productName = req.getParameter("productName");
		String type = req.getParameter("type");
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		int id = getNextId(req);
		
		Movement movement = new Movement(id, productName, type, quantity);	
		addMovementInSession(req, movement);
	}
	
	private void addMovementInSession(HttpServletRequest req, Movement movement) {
		ArrayList<Movement> movements = getSessionMovements(req);
		boolean movementAlreadyExists = movementAlreadyExists(req, movement);
		
		if (movementAlreadyExists) {
			movements = updateMovementList(req, movement);
		} else {
			movements.add(movement);
		}
		
		setMovementInSession(req, movements);
	}
	
	public void setMovementInSession(HttpServletRequest req, ArrayList<Movement> movements) {
		req.getSession().setAttribute(MOVEMENT, movements);
	}
	
	private int getNextId(HttpServletRequest req) {
		int id = getMovementsCount(req);
		
		return id + 1;
	}
	
	private int getMovementsCount(HttpServletRequest req) {
		ArrayList<Movement> movements = (ArrayList<Movement>) req.getSession().getAttribute(MOVEMENT);
		
		if (movements != null) return movements.size();
		
		return 0;
	}
	
	public List<Movement> getAllMovements(HttpServletRequest req) {
		List<Movement> listMovements = getSessionMovements(req);
		
		return listMovements;
	}
	
	private ArrayList<Movement> getSessionMovements(HttpServletRequest req) {
		ArrayList<Movement> movements = (ArrayList<Movement>) req.getSession().getAttribute(MOVEMENT);
			
		if (movements == null) movements = new ArrayList<Movement>();
		
		return movements;
	}
	
	private boolean movementAlreadyExists(HttpServletRequest req, Movement movement) {
		ArrayList<Movement> movements = getSessionMovements(req);
		
		for(Movement mov : movements) {
			if(mov != null && mov.getId() == movement.getId()) {
		        return true;
		    }
		}
		
		return false;
	}
	
	private ArrayList<Movement> updateMovementList(HttpServletRequest req, Movement movement) {
		ArrayList<Movement> movements = getSessionMovements(req);
		
		for(Movement mov : movements) {
			if(mov != null && mov.getId() == movement.getId()) {
		        mov.setProductName(movement.getProductName());
		        mov.setQuantity(movement.getQuantity());
		        mov.setType(movement.getType());
		    }
		}
		
		return movements;
	}
	
	public Movement getMovementById(HttpServletRequest req, int id) {
		ArrayList<Movement> movements = getSessionMovements(req);
		
		for(Movement mov : movements) {
			if(mov != null && mov.getId() == id) {
				return mov;
		    }			
		}
		
		return null;
	}
	
	public void editMovement(HttpServletRequest req) {
		int id = Integer.parseInt(req.getParameter("id"));
		Movement movement = getMovementById(req, id);
		
		movement.setType(req.getParameter("type"));
		movement.setQuantity(Integer.parseInt(req.getParameter("quantity")));
		
		addMovementInSession(req, movement);
	}
	
	public void deleteMovement(HttpServletRequest req) {
		ArrayList<Movement> movements = getSessionMovements(req);
		int id = Integer.parseInt(req.getParameter("id"));
		movements.removeIf(m -> m.getId() == id);
		
		setMovementInSession(req, movements);
	}
}
