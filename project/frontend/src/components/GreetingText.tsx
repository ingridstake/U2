import '../styles/greetingText.css';
import NrEvents from './NrEvents';

/**
 * A greeting text.
 * @returns greeting to tickster.com
 */
function GreetingText() {
    return (
        <>
            <div className="text">
                <h1>
                    Hej! 
                    <br/>
                    Just nu har vi <NrEvents/> evenemang att välja bland. 
                    <br/>
                    Vad är du intresserad av?
                </h1>
            </div>
        </>
    )
}

export default GreetingText;