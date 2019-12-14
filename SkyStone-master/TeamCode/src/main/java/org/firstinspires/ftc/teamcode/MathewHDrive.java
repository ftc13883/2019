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



@TeleOp(name = "MathewHDrive", group = "Tank")
public class MathewHDrive extends OpMode {
    //declares all the motors to be used later
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor claspmover;
    Servo rightServo;
    Servo leftServo;
    Servo claspServo;




    @Override
    public void init() {
        //further initializes the motors and maps it on the hardware of the revhub
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        rightServo = hardwareMap.servo.get("rightServo");
        leftServo = hardwareMap.servo.get("leftServo");
        claspServo = hardwareMap.servo.get("claspServo");

        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);




    }

    @Override
    public void loop() {
        //this block of code maps the remote controls to the movement of the motors
        frontLeft.setPower(gamepad1.left_stick_y / 1.0);
        backLeft.setPower(gamepad1.left_stick_y / 1.0);
        frontRight.setPower(gamepad1.left_stick_y / 1.0);
        backRight.setPower(gamepad1.left_stick_y / 1.0);

        //middle.setPower(gamepad1.left_stick_x / 1.0);
        frontLeft.setPower(-gamepad1.right_stick_x / 1.0);
        backLeft.setPower(-gamepad1.right_stick_x / 1.0);

        frontRight.setPower(gamepad1.right_stick_x / 1.0);
        backRight.setPower(gamepad1.right_stick_x / 1.0);

        frontRight.setPower(gamepad1.right_trigger / 1.0);
        backRight.setPower(gamepad1.right_trigger / 1.0);
        frontLeft.setPower(gamepad1.left_trigger / 1.0);
        backLeft.setPower(gamepad1.left_trigger / 1.0);


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


        //the following code maps the controller's gamepad feature ("X Y A B" buttons) to the functionality of the robot

        if (gamepad1.a == true) {
            frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
            //middle.setDirection(DcMotorSimple.Direction.FORWARD);
            //middle.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        if (gamepad1.b == true) {
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
            backRight.setDirection(DcMotorSimple.Direction.FORWARD);
            //middle.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        claspmover.setPower(gamepad2.left_trigger / 1.0);
        claspmover.setPower(-gamepad2.right_trigger / 1.0);

        if (gamepad2.b == true) {
            leftServo.setPosition(0);
            rightServo.setPosition(1);
        }

        if (gamepad2.x == true) {
            leftServo.setPosition(1);
            rightServo.setPosition(0);
        }

        if (gamepad2.y == true) {
            claspServo.setPosition(0);
        }

        if (gamepad2.a == true) {
            claspServo.setPosition(1);
            //claspServo.setPosition(0);
        }

    }
}
