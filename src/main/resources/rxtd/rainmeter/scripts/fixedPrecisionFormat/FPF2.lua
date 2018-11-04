local tSuffixes = {}
local nDivisor = 1024
local nInitScale = 1
local nDivisorReverse = 0
local bUseUpdate = false
local nDefaultPrecision = 4

local metricSuffixes = { "", "k", "M", "G", "T", "P", "E", "Z", "Y" }

function Initialize()
    local sSuffix1 = SELF:GetOption('Suffix1', ' ')
    local sPostfix = SELF:GetOption('Postfix', '')
    local array = SELF:GetOption('Suffixes')
    if (array ~= '') then
        local tmp = loadstring('return ' .. array);
        if (tmp ~= nil and type(tmp) ~= 'table') then
            tSuffixes = tmp()
        else
            tSuffixes = metricSuffixes
            SKIN:Bang('!Log', 'Suffixes are incorrect, falling to default', 'Warning')
        end
    else
        tSuffixes = metricSuffixes
    end
    tSuffixes[0] = #tSuffixes
    if (tSuffixes[0] == 0) then
        tSuffixes = { '' }
        tSuffixes[0] = #tSuffixes
    end
    for i = 1, tSuffixes[0] do
        tSuffixes[i] = sSuffix1 .. tSuffixes[i] .. sPostfix
    end
    nDivisor = SELF:GetNumberOption('Divisor', nDivisor)
    if (nDivisor <= 1) then
        nDivisor = 1024
    end
    nInitScale = SELF:GetNumberOption('InitScale', nInitScale)
    nDivisorReverse = 1.0 / nDivisor

    bUseUpdate = SELF:GetNumberOption('UseUpdate', bUseUpdate) ~= 0
    nDefaultPrecision = SELF:GetNumberOption('DefaultPrecision', nDefaultPrecision)
    return
end

local function sign(x)
    return x > 0 and 1 or x < 0 and -1 or 0
end

function FormatFormula(sInputFormula, sPrecision)
    local sInput = SKIN:ParseFormula('(' .. (sInputFormula or '0') .. ')')
    return FormatNumber(sInput, sPrecision)
end

function FormatVariable(sInputVariable, sPrecision)
    local sInput = SKIN:GetVariable(sInputVariable, '0')
    return FormatNumber(sInput, sPrecision)
end

function FormatNumber(sInputValue, sPrecision)
    local nValue = tonumber(sInputValue) or 0
    nValue = nValue * nInitScale
    local nAbsValue = math.abs(nValue)

    local nPrecision = math.floor(tonumber(sPrecision)) or 3
    if nPrecision <= 0 then
        nPrecision = 3
    end

    local nBarrierValue = 1
    for i = 1, nPrecision do
        nBarrierValue = nBarrierValue * 10
    end
    if (nBarrierValue > nDivisor) then
        nBarrierValue = nDivisor
    end

    local nDivCount = 1
    while (nAbsValue >= nBarrierValue and nDivCount < tSuffixes[0]) do
        nAbsValue = nAbsValue * nDivisorReverse
        nDivCount = nDivCount + 1
    end

    local nDigitsBeforeDecimal = 0
    if (nAbsValue > 1) then
        local nScaledValue = nAbsValue
        while (nScaledValue >= 1) do
            nDigitsBeforeDecimal = nDigitsBeforeDecimal + 1
            nScaledValue = nScaledValue * 0.1
        end
    else
        nDigitsBeforeDecimal = 1
    end
    local nDigitsAfterDecimal = math.max(0, nPrecision - nDigitsBeforeDecimal)

    return string.format("%." .. nDigitsAfterDecimal .. "f", nAbsValue * sign(nValue)) .. tSuffixes[nDivCount]
end

function Update()
    if (bUseUpdate == false) then
        return 'updates ignored'
    end

    local value = SELF:GetNumberOption('Formula', 0)
    return FormatNumber(value, nDefaultPrecision)
end
