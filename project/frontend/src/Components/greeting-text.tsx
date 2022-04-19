import React from "react";
import Form  from "react-bootstrap/Form";
import './style/greeting-text.css';


function Greeting_text() {
    return (
    <>
        <div className="text">
            <h1>
            Hej! 
            <br />
            Just nu har 10 evenemang att välja bland. 
            <br />
            Vad är du intresserad av?
            </h1>
        </div>
    </>
    )
}

export default Greeting_text;