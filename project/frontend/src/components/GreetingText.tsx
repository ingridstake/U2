import '../styles/greetingText.css';
import axios from "axios";
import React, { useEffect, useState } from "react";

/**
 * A greeting text.
 * @returns greeting to tickster.com
 */
function GreetingText() {
    const [nrEvents, setNrEvents] = useState({
        nr: "" as String
    });

    useEffect(() => {
        setNrEvents({nr: ""})
        const apiUrl = 'http://localhost:8080/number_events';
        axios.get(apiUrl).then(res => {
            const result = res.data
            setNrEvents({nr: result})
        })
    }, [setNrEvents])

    return (
        <>
            <div className="text">
                <h1>
                    Hej! 
                    <br/>
                    Just nu har vi <span className='yellow'>{nrEvents.nr}</span> evenemang att välja bland. 
                    <br/>
                    Vad är <span className='yellow'>du</span> intresserad av?
                </h1>
            </div>
        </>
    )
}

export default GreetingText;