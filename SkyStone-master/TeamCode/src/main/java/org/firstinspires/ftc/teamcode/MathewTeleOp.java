/*
    WRITE DSCRPTION OF (HDRIVE) HERE
*/

/*
360 degree servo docs
CRServo servo_name;
servo_name = hardwareMap.crservo.get("name");
servo_name.setPower(1.0); // or -1, or 0.
 */

package org.firstinspires.ftc.teamcode;

//importing the necessary packages to compatibilize our code with the robot
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name = "NewMathewDrive", group = "Tank")
public class MathewTeleOp extends OpMode {
    MathewHardwarePushbot         robot   = new MathewHardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1120 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 0.025 ;     // this is 1/40
    static final double     WHEEL_DIAMETER_INCHES   = 4.0;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 1.0; static final double     TURN_SPEED              = 5.0;




    @Override
    public void init() {
        //further initializes the motors and maps it on the hardware of the revhub
        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "MathewTeleOp.java: Resetting Encoders");    //
        telemetry.update();



    }

    @Override
    public void loop() {
        //this does everything don't question

        robot.motorMovementTeleOpLoop(gamepad1,gamepad2);

        /*
        This block was simply an example of unit testing and debugging. In this specific case, Mathew was in fact the block of code to see what the current
        telemetry.addData("Path0", "Starting at %7d :%7d :%7d :%7d",
                frontLeft.getCurrentPosition(),
                frontRight.getCurrentPosition(),
                backLeft.getCurrentPosition(),
                backRight.getCurrentPosition());
        //robot.middle.getCurrentPosition())
        //telemetry.update();
       */
    }
}
