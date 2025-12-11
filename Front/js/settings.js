// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
// 各ゲームのルール
const gameData = {
    // -----------------------------------------------------------
    // ホゲホゲ
    hogehoge: {
        title: "エラー",
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
        maxPlayers: 4
    }
    // -----------------------------------------------------------
};
// ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
// 画面に反映
// -----------------------------------------------------------
// URLパラメータからゲームIDを取得
const url = new URL(window.location.href);
const gameID = url.searchParams.get("game") || "hogehoge"; //  デフォルトはhogehoge
const gameInfo = gameData[gameID];

// ゲームルール
const ruleArea = document.getElementById("ruleArea");
const btn = document.getElementById("toggleDetailBtn");
const detail = document.getElementById("gameRuleDetail");
// 選択人数の指定
const icons = document.querySelectorAll('.player-icon');
const minP = gameInfo.minPlayers;
const maxP = gameInfo.maxPlayers;

// タイトル表示
document.getElementById("gameTitle").textContent = gameInfo.title;

if (gameID === "hogehoge") {
    btn.textContent = "";
    // 人数選択アイコンを全て非表示
    icons.forEach(icon => {
        icon.style.display = "none";
    });
    // エラー文言の表示
    document.getElementById("minPlayersValue").innerHTML = `エラーが発生したため選択できません`;

} else {

    // -----------------------------------------------------------

    // ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■
    // -----ルールの表示-----
    document.addEventListener("DOMContentLoaded", () => {
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

        // 【要修正】　ゲームに必要な人数分の背景色をつける
        updateMinArea();

        let selectedCount = minP;

        // 表示1つめのアイコンを「YOU」に設定
        icons[0].src = "../img/player_you.png";

        // 最大参加人数以上のアイコンを非表示にする
        icons.forEach(icon => {
            const idx = Number(icon.dataset.index);
            if (idx > maxP) {
                icon.style.display = "none";
            }
        });

        updateSelectedIcons();

        icons.forEach(icon => {
            // マウスがアイコンに乗った時の動き
            icon.addEventListener('mouseover', () => {
                const idx = Number(icon.dataset.index);

                icons.forEach(i => {
                    const iIdx = Number(i.dataset.index);

                    if (iIdx <= minP) return; // 最低必要人数内は画像が切り替わらない
                    if (i.style.display === "none") return; // 非表示は対象外
                    i.src = (iIdx <= idx) ? "../img/player_hover.png" : "../img/player.png";
                });
            });

            /*
            マウスが外れたら「仮表示」を消して、実際に確定している選択状態に戻す。*/
            icon.addEventListener('mouseout', () => {
                updateSelectedIcons();
            });

            /*
            クリックで selectedCount を更新（ただし最低 2 人に制限）。その後画面反映関数を呼ぶ。*/
            icon.addEventListener('click', () => {
                const idx = Number(icon.dataset.index);

                // 最低参加人数以下のアイコンが選択された時　または　それ以外の時
                if (idx <= minP) {
                    // 最低参加人数が参加人数にセットされる
                    selectedCount = minP;
                } else {
                    // クリックされたアイコン分の人数が参加人数にセットされる
                    selectedCount = idx;
                }
                updateSelectedIcons();
            });
        });

        // 【要修正】
        function updateMinArea() {
            const icons = document.querySelectorAll('.player-icon');
            const bg = document.getElementById('minAreaBg');
            const parent = document.querySelector('.player-select').getBoundingClientRect();

            const first = icons[0].getBoundingClientRect();
            const last = icons[minP - 1].getBoundingClientRect();

            bg.style.left = (first.left - parent.left) + "px";
            bg.style.top = (first.top - parent.top) + "px";
            bg.style.width = (last.right - first.left) + "px";
            bg.style.height = first.height + "px";
        }

        /*
        実際にアイコン群をループして src を選択状態に合わせて書き換える。
        最後に selectedValue 要素に選択人数を表示する。*/
        function updateSelectedIcons() {
            icons.forEach(i => {
                if (i.style.display === "none") return; // ← 非表示は対象外
                const idx = Number(i.dataset.index);
                if (idx === 1) {
                    // i.src = "../img/player_you.png";
                } else if (idx <= selectedCount) {
                    i.src = "../img/player_hover.png";
                } else {
                    i.src = "../img/player.png";
                }
            });
            document.getElementById("minPlayersValue").innerHTML = `ゲームに必要な人数　：　${gameInfo.minPlayers}人〜　【${selectedCount}人選択中】`;
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
}
