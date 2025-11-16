```bash name=EDITOR-USAGE.md
``` 
# Editor 菴ｿ逕ｨ繧ｬ繧､繝会ｼ医Ο繝ｼ繧ｫ繝ｫ邱ｨ髮・Ρ繝ｼ繧ｯ繝輔Ο繝ｼ / 謗ｨ螂ｨ・・
縺薙・繝峨く繝･繝｡繝ｳ繝医・縲√Ο繝ｼ繧ｫ繝ｫ縺ｧ縺ｮ邱ｨ髮・Ρ繝ｼ繧ｯ繝輔Ο繝ｼ・域耳螂ｨ: 繝ｯ繝ｼ繧ｯ繧ｹ繝壹・繧ｹ縺ｫ繝ｪ繝昴ず繝医Μ繧呈欠螳壹＠縺ｦ逶ｴ謗･邱ｨ髮・☆繧区婿豕包ｼ峨↓縺､縺・※縺ｾ縺ｨ繧√◆繧ゅ・縲・
蜑肴署
- 繝ｭ繝ｼ繧ｫ繝ｫ縺ｫ縺薙・繝ｪ繝昴ず繝医Μ繧・clone 縺励※縺・ｋ縺薙→・井ｾ・ C:\projects\Ruichiji-Website・・- ruichiji-editor・医ョ繧ｹ繧ｯ繝医ャ繝励い繝励Μ・峨ｒ莉ｻ諢上・蝣ｴ謇縺ｫ鄂ｮ縺阪゛DK 17 縺後う繝ｳ繧ｹ繝医・繝ｫ縺輔ｌ縺ｦ縺・ｋ縺薙→

蝓ｺ譛ｬ繝ｯ繝ｼ繧ｯ繝輔Ο繝ｼ・亥ｮ牙・縺ｧ謗ｨ螂ｨ縺輔ｌ繧区焔鬆・ｼ・1. 譛譁ｰ繧貞叙蠕・```
cd C:\projects\Ruichiji-Website
git checkout main
git pull origin main
```

2. 繝悶Λ繝ｳ繝∽ｽ懈・・井ｽ懈･ｭ縺ｯ蠢・★繝悶Λ繝ｳ繝√〒・・```
git checkout -b edit/<what-you-change>-YYYYMMDD
```

3. 繧ｨ繝・ぅ繧ｿ襍ｷ蜍・   - ruichiji-editor 繧定ｵｷ蜍輔＠縲√Ρ繝ｼ繧ｯ繧ｹ繝壹・繧ｹ縺ｨ縺励※縺薙・繝ｪ繝昴ず繝医Μ・・:\projects\Ruichiji-Website・峨ｒ驕ｸ謚槭＠縺ｾ縺吶・   - 蟾ｦ繝壹う繝ｳ縺ｧ邱ｨ髮・ｯｾ雎｡・・opics, Music, Movies, Discography, Live・峨ｒ驕ｸ縺ｳ縲∫ｷｨ髮・・菫晏ｭ倥＠縺ｦ縺上□縺輔＞縲・
4. 螟画峩遒ｺ隱・```
git status
git diff
```

5. 螟画峩繧偵せ繝・・繧ｸ縺励※繧ｳ繝溘ャ繝・```
git add assets/data/*.json assets/images/*
git commit -m "feat: Update <kind> 窶・<short description>"
```

6. 繝悶Λ繝ｳ繝√ｒ push 竊・Pull Request 繧剃ｽ懈・
```
git push origin HEAD
```
   - GitHub 縺ｧ PR 繧剃ｽ懈・縺励∝ｿ・ｦ√↓蠢懊§縺ｦ繝ｬ繝薙Η繝ｼ繧貞女縺代※繝槭・繧ｸ縺励※縺上□縺輔＞縲・
豕ｨ諢丈ｺ矩・- DataService 縺ｯ菫晏ｭ俶凾縺ｫ繝舌ャ繧ｯ繧｢繝・・繝輔ぃ繧､繝ｫ・・ssets/data/{kind}.json.bak-<timestamp>・峨ｒ菴懈・縺励∪縺吶ゅ％繧後ｉ縺ｯ .gitignore 縺ｧ辟｡隕悶＆繧後※縺・∪縺吶・- 螟ｧ縺阪↑逕ｻ蜒上ｄ隱､縺｣縺溘ヱ繧ｹ繧偵さ繝溘ャ繝医＠縺ｪ縺・ｈ縺・↓豕ｨ諢上＠縺ｦ縺上□縺輔＞縲Ｄover 遲峨・繝代せ縺ｯ `assets/images/<filename>` 縺ｮ逶ｸ蟇ｾ繝代せ縺ｧ邨ｱ荳縺励※縺上□縺輔＞縲・- 隍・焚莠ｺ縺悟酔縺倥ヵ繧｡繧､繝ｫ繧堤ｷｨ髮・☆繧句ｴ蜷医・縲√・繝ｼ繧ｸ繧ｳ繝ｳ繝輔Μ繧ｯ繝医↓豕ｨ諢上＠縲・←螳・pull 竊・resolve 竊・commit 繧定｡後▲縺ｦ縺上□縺輔＞縲・'@

$gitignore = @'
# Ignore DataService backup files
assets/data/*.json.bak-*

# Ignore editor builds (if you place editor inside repo/tools/editor)
tools/editor/dist/
tools/editor/build/
tools/editor/*.exe
tools/editor/*.zip

# Node/npm / IDE / OS files (examples)
node_modules/
.vscode/
*.log
Thumbs.db
.DS_Store
