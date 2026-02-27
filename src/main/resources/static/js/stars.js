// stars.js (with cleanup)
const canvas = document.getElementById('star-canvas');
const ctx = canvas.getContext('2d');

let width = canvas.width = window.innerWidth;
let height = canvas.height = window.innerHeight;

window.addEventListener('resize', () => {
    width = canvas.width = window.innerWidth;
    height = canvas.height = window.innerHeight;
});

const starCount = 100;
const stars = [];

for (let i = 0; i < starCount; i++) {
    const baseAlpha = Math.random() * 0.8 + 0.2;
    const twinkleSpeed = Math.random() * 0.02 + 0.01;
    const phase = Math.random() * Math.PI * 2;

    stars.push({
        x: Math.random() * width,
        y: Math.random() * height,
        radius: Math.random() * 1.5 + 0.5,
        baseAlpha,
        twinkleSpeed,
        phase
    });
}

let animationId;

function animateStars(time) {
    ctx.clearRect(0, 0, width, height);
    for (let star of stars) {
        const alpha = star.baseAlpha + Math.sin(time * star.twinkleSpeed + star.phase) * 0.3;
        ctx.beginPath();
        ctx.arc(star.x, star.y, star.radius, 0, Math.PI * 2);
        ctx.fillStyle = `rgba(255,255,255,${Math.max(0, Math.min(1, alpha))})`;
        ctx.fill();
    }
    setTimeout(() => {
        requestAnimationFrame(animateStars);
    }, 1000 / 30); // 30 FPS
}

// start animation
animationId = requestAnimationFrame(animateStars);

// cleanup on page unload
window.addEventListener('beforeunload', () => {
    if (animationId) cancelAnimationFrame(animationId);
});