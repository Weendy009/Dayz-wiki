const weaponName = document.getElementById('weapon-name');
const meleeButtons = document.getElementById('melee-buttons');
const ppButton = document.getElementById('pp-buttons');
const rangedButtons = document.getElementById('ranged-buttons');
const riflesButtons = document.getElementById('rifle-buttons');
const shotgunsButtons = document.getElementById('shotgun-buttons');
const prevButton = document.getElementById('prev-button');
const nextButton = document.getElementById('next-button');

const weaponModes = [
    'Холодное оружие',
    'Пистолеты',
    'Пистолеты-пулемёты',
    'Винтовки',
    'Дробовики'
];

let currentModeIndex = 0;

function updateWeaponMode() {
    weaponName.textContent = weaponModes[currentModeIndex];
    if (currentModeIndex === 0) {
        meleeButtons.style.display = 'block';
        rangedButtons.style.display = 'none';
        ppButton.style.display = 'none';
        riflesButtons.style.display = 'none';
        shotgunsButtons.style.display = 'none';
    } else if (currentModeIndex === 1) {
        meleeButtons.style.display = 'none';
        rangedButtons.style.display = 'block';
        ppButton.style.display = 'none';
        riflesButtons.style.display = 'none';
        shotgunsButtons.style.display = 'none';
    } else if (currentModeIndex === 2) {
        meleeButtons.style.display = 'none';
        rangedButtons.style.display = 'none';
        ppButton.style.display = 'block';
        riflesButtons.style.display = 'none';
        shotgunsButtons.style.display = 'none';
    } else if (currentModeIndex === 3) {
        meleeButtons.style.display = 'none';
        rangedButtons.style.display = 'none';
        ppButton.style.display = 'none';
        riflesButtons.style.display = 'block';
        shotgunsButtons.style.display = 'none';
    } else if (currentModeIndex === 4) {
        meleeButtons.style.display = 'none';
        rangedButtons.style.display = 'none';
        ppButton.style.display = 'none';
        riflesButtons.style.display = 'none';
        shotgunsButtons.style.display = 'block';
    }
}

prevButton.addEventListener('click', () => {
    currentModeIndex = (currentModeIndex - 1 + weaponModes.length) % weaponModes.length;
    updateWeaponMode();
});

nextButton.addEventListener('click', () => {
    currentModeIndex = (currentModeIndex + 1) % weaponModes.length;
    updateWeaponMode();
});

updateWeaponMode();