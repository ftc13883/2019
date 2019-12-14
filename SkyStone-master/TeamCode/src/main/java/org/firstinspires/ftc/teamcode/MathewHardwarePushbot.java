/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "left_arm"
 * Servo channel:  Servo to open left claw:  "left_hand"
 * Servo channel:  Servo to open right claw: "right_hand"
 */
public class MathewHardwarePushbot
{
    /* Public OpMode members. */
    public DcMotor  leftFront   = null;
    public DcMotor  rightFront  = null;
    public DcMotor  leftBack = null;
    public DcMotor  rightBack = null;
    public DcMotor  liftMech = null;
    public DcMotor  middle = null;
    public Servo rightFoundation = null;
    public Servo leftFoundation = null;
    public Servo blockClasp = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public MathewHardwarePushbot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftFront  = hwMap.get(DcMotor.class, "leftFront");
        rightFront = hwMap.get(DcMotor.class, "rightFront");
        leftBack = hwMap.get(DcMotor.class, "leftBack");
        rightBack = hwMap.get(DcMotor.class, "rightBack");
        //middle = hwMap.get(DcMotor.class, "middle")
        liftMech = hwMap.get(DcMotor.class, "liftMech");

        //Initialize servos
        rightFoundation = hwMap.get(Servo.class, "rightFoundation");
        leftFoundation = hwMap.get(Servo.class, "leftFoundation");
        blockClasp = hwMap.get(Servo.class, "blockClasp");

        //Set directions of dc motors
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        liftMech.setDirection(DcMotor.Direction.FORWARD);
        //middle.setDirection(DcMotor.Direction.FORWARD)


        // Set all motors to zero power
        leftFront.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
        leftBack.setPower(0);
        //middle.setPower(0);
        liftMech.setPower(0);


        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //middle.setMode(DcMotor.RunMode.RUN_USING_ENCODERS);
        liftMech.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        //Initialize foundation movers to be in the up position
        foundationUp();

    }
    public void motorMovementTeleOpLoop(Gamepad gamepad1, Gamepad gamepad2) {
        leftFront.setPower(gamepad1.left_stick_y / 1.0);
        leftBack.setPower(gamepad1.left_stick_y / 1.0);
        rightFront.setPower(gamepad1.left_stick_y / 1.0);
        rightBack.setPower(gamepad1.left_stick_y / 1.0);

        leftFront.setPower(-gamepad1.right_stick_x / 1.0);
        leftBack.setPower(-gamepad1.right_stick_x / 1.0);

        rightFront.setPower(gamepad1.right_stick_x / 1.0);
        rightBack.setPower(gamepad1.right_stick_x / 1.0);

        rightFront.setPower(gamepad1.right_trigger / 1.0);
        rightBack.setPower(gamepad1.right_trigger / 1.0);
        leftFront.setPower(gamepad1.left_trigger / 1.0);
        leftBack.setPower(gamepad1.left_trigger / 1.0);

        //middle.setPower(gamepad1.left_stick_x / 1.0);

        //Move lift of epic
        liftMech.setPower(gamepad2.right_stick_y/1.0);

        /*
            Gamepad2 of Epic
            A = Open Block Clasp
            Y = Close Block Clasp
            X = Put foundation mover down
            B = Put foundation mover up
         */
        if(gamepad2.a == true){
            blockClasp.setPosition(1.0);
        } else if (gamepad2.y == true){
            blockClasp.setPosition(0.0);
        }

        if(gamepad2.x == true){
            foundationDown();
        } else if (gamepad2.b == true){
            foundationUp();
        }
    }

    //move both foundation movers up
    public void foundationUp(){
        rightFoundation.setPosition(1.0);
        leftFoundation.setPosition(1.0);
    }

    //move both foundation movers down
    public void foundationDown(){
        rightFoundation.setPosition(0.0);
        leftFoundation.setPosition(0.0);
    }
 }

