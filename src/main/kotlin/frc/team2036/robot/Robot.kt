package frc.team2036.robot

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.XboxController
import edu.wpi.first.wpilibj.drive.DifferentialDrive

const val XBOX_CONTROLLER_PORT = 0
const val LEFT_TALON_PORT = 3
const val RIGHT_TALON_PORT = 0

/**
 * The main and only class for the DDR bot
 */
class Robot : IterativeRobot() {

    lateinit var controller: Joystick
    lateinit var driveTrain: DifferentialDrive

    /**
     * Initializer that sets up the xbox controller and the drivetrain
     */
    override fun teleopInit() {
        this.controller = Joystick(XBOX_CONTROLLER_PORT)
        this.driveTrain = DifferentialDrive(WPI_TalonSRX(LEFT_TALON_PORT), WPI_TalonSRX(RIGHT_TALON_PORT))
    }

    /**
     * Polls the DDR mat and then runs the robot
     */
    override fun teleopPeriodic() {
        val limit = .5
        var x = 0.0
        var y = 0.0
//        for (i in 1..16) {
//            if (this.controller.getRawButtonPressed(i)) {
//                println("WORKING FOR PORT $i")
//            }
//        }
        when {
            this.controller.getRawButton(13) -> y = limit
            this.controller.getRawButton(15) -> y = -limit
            this.controller.getRawButton(16) -> x = -limit
            this.controller.getRawButton(14) -> x = limit
        }
//        println(x)
//        println(y)
        this.driveTrain.tankDrive(x, y)
    }

}