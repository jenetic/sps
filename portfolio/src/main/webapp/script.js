// Typewriter Effect
const typeEffect = (content, speed) => {
    let i = 0;
    let text = content;
    let interval = setInterval(function(){
        document.getElementById("name").innerHTML += content.charAt(i);
        i++;
        if (i >= text.length) {
            clearInterval(interval);
        }
    }, speed)
  }
  typeEffect("Hi! I'm Jenny.", 120); 