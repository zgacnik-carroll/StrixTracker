// stars.js

const canvas = document.getElementById('star-canvas');
const ctx = canvas.getContext('2d');

let width = canvas.width = window.innerWidth;
let height = canvas.height = window.innerHeight;

window.addEventListener('resize', () => {
    width = canvas.width = window.innerWidth;
    height = canvas.height = window.innerHeight;
});

// Create stars
const starCount = 150;
const stars = [];

for (let i = 0; i < starCount; i++) {
    stars.push({
        x: Math.random() * width,
        y: Math.random() * height,
        radius: Math.random() * 1.5 + 0.5,
        alpha: Math.random(),
        delta: Math.random() * 0.02 + 0.005
    });
}

// Animate stars
function animateStars() {
    ctx.clearRect(0, 0, width, height);

    for (let star of stars) {
        // twinkle
        star.alpha += star.delta;
        if (star.alpha > 1 || star.alpha < 0) star.delta *= -1;

        ctx.beginPath();
        ctx.arc(star.x, star.y, star.radius, 0, Math.PI * 2);
        ctx.fillStyle = `rgba(255,255,255,${star.alpha})`;
        ctx.fill();
    }

    requestAnimationFrame(animateStars);
}

animateStars();