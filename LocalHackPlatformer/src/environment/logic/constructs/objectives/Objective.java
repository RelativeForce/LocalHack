package environment.logic.constructs.objectives;

/**
 * Describe a objective that will perform an action when intercepted by the player.
 * @author Joshua_Eddy
 *
 */
public interface Objective{

	/**
	 * When the objective is intercepted by the player, this performs the action associated with that objective.
	 */
	public void action();
	

}
