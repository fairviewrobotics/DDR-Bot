package frc.team2036.robot

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.drive.DifferentialDrive

//The driverstation port where the DDR mat is connected
const val DDR_MAT_PORT = 0
//The port on the RoboRIO where the left talon is
const val LEFT_TALON_PORT = 3
//The port on the RoboRIO where the right talon is
const val RIGHT_TALON_PORT = 0

//How much the robot can go in either the X or Y directions in a single iteration
const val MAX_ORTHOGONAL_SPEED = 0.75

/**
 * An enumeration of directions on the DDR mat
 */
enum class Direction(val portNumber: Int) {
    UP(16),
    DOWN(14),
    LEFT(13),
    RIGHT(15)
}

/**
 * The main and only class for the DDR bot
 * Is an iterative robot which will have some of its methods periodically called
 */
class Robot : TimedRobot() {

    //The DDR mat; is of type XboxController, but doesn't really need to be an XboxController
    lateinit var controller: XboxController
    //The object that interfaces with the talons and actually handles driving them
    lateinit var driveTrain: DifferentialDrive

    /**
     * Initializer that sets up the DDR mat and the drivetrain
     * Gets called once at the beginning of the teleoperated period
     */
    override fun teleopInit() {
        this.controller = XboxController(DDR_MAT_PORT)
        this.driveTrain = DifferentialDrive(WPI_TalonSRX(LEFT_TALON_PORT), WPI_TalonSRX(RIGHT_TALON_PORT))
    }

    /**
     * Polls the DDR mat and then runs the robot
     * Gets called repeatedly during the teleoperated period
     */
    override fun teleopPeriodic() {
        //How much the robot should move in either the x or y directions of this iteration
        //Both x and y start at 0, but get updated with data from the DDR mat
        var x = 0.0
        var y = 0.0
        //Polls the DDR mat for the relevant buttons and adds to the x and y variables accordingly
        if (this.controller.getRawButton(Direction.UP.portNumber)) {
            y += MAX_ORTHOGONAL_SPEED
        }
        if (this.controller.getRawButton(Direction.DOWN.portNumber)) {
            y += -MAX_ORTHOGONAL_SPEED
        }
        if (this.controller.getRawButton(Direction.LEFT.portNumber)) {
            x += -MAX_ORTHOGONAL_SPEED
        }
        if (this.controller.getRawButton(Direction.RIGHT.portNumber)) {
            x += MAX_ORTHOGONAL_SPEED
        }
        //Tells the drivetrain to drive with the given x and y speeds that were polled from the DDR mat
        this.driveTrain.arcadeDrive(x, -y)
    }

}