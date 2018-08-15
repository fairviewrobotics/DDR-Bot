package frc.team2036.robot

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.drive.DifferentialDrive

const val XBOX_CONTROLLER_PORT = 1
const val LEFT_TALON_PORT = 2
const val RIGHT_TALON_PORT = 3

/**
 * The main and only class for the DDR bot
 */
class Robot : IterativeRobot() {

    lateinit var controller: XboxController
    lateinit var driveTrain: DifferentialDrive

    /**
     * Initializer that sets up the xbox controller and the drivetrain
     */
    override fun teleopInit() {
        this.controller = XboxController(XBOX_CONTROLLER_PORT)
        this.driveTrain = DifferentialDrive(WPI_TalonSRX(LEFT_TALON_PORT), WPI_TalonSRX(RIGHT_TALON_PORT))
    }

    /**
     * Polls the DDR mat and then runs the robot
     */
    override fun teleopPeriodic() {
        this.driveTrain.tankDrive(this.controller.x, this.controller.y)
    }

}