// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
// 各ゲームのルール
const gameData = {
    // -----------------------------------------------------------
    // ホゲホゲ
    hogehoge: {
        title: "ゲーム読み取り不可",
        // - - - - - - - - - - - - - - - - - - - -
        rule: `<p>バグってる。どんまい。</p>`,
        // - - - - - - - - - - - - - - - - - - - -
        //賭けなし
        betting: 0,
        // - - - - - - - - - - - - - - - - - - - -
        minPlayers: 0,
        maxPlayers: 0
    },
    // -----------------------------------------------------------
    // ババ抜き
    babanuki: {
        title: "ババ抜き",
        // - - - - - - - - - - - - - - - - - - - -
        rule: `<p>ジョーカーを残したままカードを引いていきます。
手札がなくなった人が勝利です。</p>`,
        // - - - - - - - - - - - - - - - - - - - -
        //賭けなし
        betting: 0,
        // - - - - - - - - - - - - - - - - - - - -
        minPlayers: 2,
        maxPlayers: 6
    },
    // -----------------------------------------------------------
    // 七並べ
    sevens: {
        title: "七並べ",
        // - - - - - - - - - - - - - - - - - - - -
        rule: `<p>7を中心にカードを並べていきます。<br/>
手札を早くなくした人が勝ちです。</p>`,
        // - - - - - - - - - - - - - - - - - - - -
        //賭けなし
        betting: 0,
        // - - - - - - - - - - - - - - - - - - - -
        minPlayers: 2,
        maxPlayers: 6
    },
    // -----------------------------------------------------------
    // インディアンポーカー
    indianpoker: {
        title: "インディアンポーカー",
        // - - - - - - - - - - - - - - - - - - - -
        rule: `
<p>カードが各プレイヤーに一枚ずつ配られます。<br/>
自分のカードは見れませんが、他プレイヤーのカードは見えます。<br/>
他プレイヤーからのヒントを参考に勝負するか決めるゲームです。<br/><br/>
<b>【カードの強さ】</b><br/>
数字の中では　最弱：3　最強：2<br/>
ただし、「JOKER」は「2」よりも強いが、「3」にだけ負ける。</p>
<img src="../img/DiscImg_IndiP.png" alt="カード強さ表" style="width:100%; max-width:600px;">`,
        // - - - - - - - - - - - - - - - - - - - -
        //賭けあり
        betting: 1,
        // - - - - - - - - - - - - - - - - - - - -
        minPlayers: 2,
        maxPlayers: 6
    }
    // -----------------------------------------------------------
};
// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
// 画面に反映
// -----------------------------------------------------------
// URLパラメータからゲームIDを取得
const url = new URL(window.location.href);
const gameID = url.searchParams.get("game") ; // 「|| "hogehoge"」デフォルトはhogehogeの予定

const gameInfo = gameData[gameID];

// -----------------------------------------------------------
// タイトル表示
document.getElementById("gameTitle").textContent = gameInfo.title;

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
// -----ルールの表示-----
document.addEventListener("DOMContentLoaded", () => {
    const ruleArea = document.getElementById("ruleArea");
    const btn = document.getElementById("toggleDetailBtn");
    const detail = document.getElementById("gameRuleDetail");

    btn.addEventListener("click", () => {
        const isHidden = detail.classList.contains("hidden");

        if (isHidden) {
            // ルール文を表示する
            detail.classList.remove("hidden"); // ボックスの隙間を表示させる
            ruleArea.innerHTML = gameInfo.rule; // ルール文の表示
            btn.textContent = "ゲームルール ▼"; // ボタン表示文字
        } else {
            // ルール文を表示しない
            detail.classList.add("hidden");  // ボックスの隙間を無くす
            ruleArea.innerHTML = null; // ルール文の非表示
            btn.textContent = "ゲームルール ▶︎"; //ボタン表示文字
        }
    });
});
// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
// -----参加人数の設定-----
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("minPlayersValue").textContent = `参加人数を指定してください。　※ 最低参加人数：${gameInfo.minPlayers}人`;
    // 人数選択UI
    const icons = document.querySelectorAll('.player-icon');
    let selectedCount = gameInfo.minPlayers; // 最低人数は固定

    // 1人目は自分固定
    icons[0].src = "../img/player_you.png";
    icons[0].classList.add("fixed");

    // 初期状態で2人目を選択済みにしておく
    selectedCount = 2;
    updateSelectedIcons();

    // マウスイベントとクリックで人数選択
    icons.forEach(icon => {

        icon.addEventListener('mouseover', () => {
            const idx = Number(icon.dataset.index);
            icons.forEach(i => {
                const iIdx = Number(i.dataset.index);
                if (iIdx === 1) return; // 自分は固定
                i.src = (iIdx <= idx) ? "../img/player_hover.png" : "../img/player.png";
            });
        });

        icon.addEventListener('mouseout', () => {
            updateSelectedIcons();
        });

        icon.addEventListener('click', () => {
            const idx = Number(icon.dataset.index);
            if (idx >= 2) selectedCount = idx; // 2人以上選択
            updateSelectedIcons();
        });
    });

    function updateSelectedIcons() {
        icons.forEach(i => {
            const idx = Number(i.dataset.index);
            if (idx === 1) {
                i.src = "../img/player_you.png";
            } else if (idx <= selectedCount) {
                i.src = "../img/player_hover.png";
            } else {
                i.src = "../img/player.png";
            }
        });
        document.getElementById('selectedValue').textContent = `test_参加人数：${selectedCount}人`;
    }
});
// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
// チップ制御
document.addEventListener("DOMContentLoaded", () => {
    //
    const moneyH = document.getElementById("amountMoneyH");
    //
    const moneyHValueDisplay = document.getElementById("moneyHValueDisplay");
    const moneyHMinLabel = document.getElementById("moneyHMinLabel");
    const moneyHMaxLabel = document.getElementById("moneyHMaxLabel");
    const coins = document.querySelectorAll(".coin");
    let selectedCoin = document.getElementById("selectedCoin");

    coins.forEach(coin => {
        coin.addEventListener("click", () => {

            // 全コイン画像の半透明
            coins.forEach(c => c.classList.remove("selected"));

            // クリックしたコインを不透明化
            coin.classList.add("selected");

            selectedCoin = Number(coin.dataset.value);

            document.getElementById('selecteCoinValue').textContent = `test_コイン：${selectedCoin}`;

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
});
// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
// スライダー値を coin の倍数に丸める
document.addEventListener("DOMContentLoaded", () => {
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
});
// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
// ▼ ② 参加費チェック
function validateEntry() {
    const entryInput = document.getElementById("entryInput");
    const entryError = document.getElementById("entryError");
    const moneyInput = document.getElementById("moneyInput");

    if (moneyInput.value.trim() === "" || entryInput.value.trim() === "") {
        entryError.textContent = "";
        return;
    }

    // 数字以外
    if (!/^\d+$/.test(entryInput.value.trim())) {
        entryError.textContent = "※数字で入力してください。";
        return;
    }

    const money = Number(moneyInput.value);
    const entry = Number(entryInput.value);

    const min = 1;
    const max = Math.floor(money / 10);

    if (entry < min || entry > max) {
        entryError.textContent =
            `(${min} 〜 ${max} の間の数字を指定してください。)`;
        return;
    }

    entryError.textContent = "";
}

// ▼ 入力中にリアルタイム検証
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("moneyInput").addEventListener("input", validateMoney);
    document.getElementById("entryInput").addEventListener("input", validateEntry);
});

// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
// 開始ボタン
document.getElementById('startGame').addEventListener('click', () => {
    // 例：人数を URL パラメータで次画面に渡す
    window.location.href = `startGame.html?players=${selectedCount}&game=${gameID}`;
});
// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■