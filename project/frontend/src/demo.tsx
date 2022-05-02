import React, { useState } from 'react';

type buttonprops = {
    startVal: number;
}

const ButtonAndText : React.FC<buttonprops> = (props : {
    startVal: number
}) => {
    const [data, setData] = useState(props.startVal)
    const onclick = () => {
        setData(data + 1)
        console.log(data)
    }
    return (
        <div style = {{borderStyle : 'solid'}}>
        <p>{data}</p>
        <button onClick={onclick}> Knapp!</button>
        </div>
    )

}

export default ButtonAndText