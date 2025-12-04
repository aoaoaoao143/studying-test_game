//
const moneyH = document.getElementById("amountMoneyH");
//
const moneyHValueDisplay = document.getElementById("moneyHValueDisplay");
const moneyHMinLabel = document.getElementById("moneyHMinLabel");
const moneyHMaxLabel = document.getElementById("moneyHMaxLabel");
const coins = document.querySelectorAll(".coin");

let selectedCoin = 1;

// コイン選択
coins.forEach(coin => {
    coin.addEventListener("click", () => {

        // 全コイン画像を非選択にする
        coins.forEach(c => c.classList.remove("selected"));

        // 選択されたコインにselectedを付与
        coin.classList.add("selected");

        // dataset.value を取得
        selectedCoin = Number(coin.dataset.value);

        // スライダー設定更新（10〜100）
        const min = 10;
        const max = 100;
        moneyH.min = min;
        moneyH.max = max;
        moneyH.step = 1;
        moneyH.value = min;

        // スライダーの左右の表示を coin × min/max で更新
        moneyHMinLabel.textContent = min * selectedCoin;
        moneyHMaxLabel.textContent = max * selectedCoin;

        updateSliderBackground();
        updateSliderValue();
    });
});

// スライダー値を coin の倍数に丸める
moneyH.addEventListener("input", () => {

    let val = Math.round(moneyH.value / selectedCoin) * selectedCoin;

    moneyH.value = val;

    updateSliderBackground();
    updateSliderValue();
});

function updateSliderBackground() {
    const percent = ((moneyH.value - moneyH.min) / (moneyH.max - moneyH.min)) * 100;
    moneyH.style.backgroundSize = `${percent}% 100%`;
}

function updateSliderValue() {
    moneyHValueDisplay.textContent = `選択値: ${moneyH.value * selectedCoin}`;
}

// 初期化（最初のコインを自動選択）
coins[0].click();

document.addEventListener("DOMContentLoaded", () => {
    const btn = document.getElementById("helloBtn");
    btn.addEventListener("click", () => alert("こんにちは！"));
});
